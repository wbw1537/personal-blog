package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.ArticleTag;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.entity.Tag;
import com.wbw1537.domain.vo.*;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.mapper.ArticleTagMapper;
import com.wbw1537.mapper.CategoryMapper;
import com.wbw1537.service.ArticleService;
import com.wbw1537.service.ArticleTagService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.wbw1537.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成responseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();

        //要求为正式文章
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);

        //降序排序
        queryWrapper.orderByDesc(Article::getViewCount);

        //限制数量（10条）
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        //获取redis中对应id的浏览量并赋值
        for(Article article : articles){
            article.setViewCount(getArticleViewCountById(article.getId()).longValue());
        }

//        //bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles){
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(hotArticleVoList);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件:
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //categoryId是否有参
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //查询文章是否正式发布
        lambdaQueryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        //筛选置顶文章（对isTop降序排序）
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //根据发布时间对文章进行降序排序
        lambdaQueryWrapper.orderByDesc(Article::getCreateTime);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        //查询categoryName（分类名称）

        //采用for循环查询
        List<Article> articles = page.getRecords();
        //用categoryID查询categoryName
        for (Article article : articles){
            Category category = categoryMapper.selectById(article.getCategoryId());
            article.setCategoryName(category.getName());
            //获取redis中对应id的浏览量并赋值
            article.setViewCount(getArticleViewCountById(article.getId()).longValue());
        }

        //采用stream流查询
//        List<Article> articles = page.getRecords();
//        articles.stream()
//                .map(article -> article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章内容
        Article article = getById(id);
        //从redis中获取viewCount
        article.setViewCount(getArticleViewCountById(id).longValue());
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);
        //根据分类id查询分类名称
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装查询结果
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应id的浏览量
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString(), 1L);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {
        // 添加博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        // 添加博客-标签表的关联关系
        articleTagService.saveBatch(articleTags);
        // 将博客的浏览量存入redis
        updateAllArticleViewCount();
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        // 判断pageNum和pageSize是否为空
        if(pageNum == null || pageSize == null){
            throw new SystemException(AppHttpCodeEnum.PARAM_INVALID);
        }

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 条件查询
        queryWrapper.like(StringUtils.hasText(title), Article::getTitle, title);
        queryWrapper.like(StringUtils.hasText(summary),Article::getSummary,summary);
        // 分页查询
        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        // 封装数据
        List<Article> articleList = page.getRecords();
        List<ArticleManagementVo> articleManagementVos = BeanCopyUtils.copyBeanList(articleList, ArticleManagementVo.class);

        PageVo pageVo = new PageVo(articleManagementVos,page.getTotal());
//        List<Article> articleList = (List<Article>) pageVo.getRows().stream().collect(Collectors.toList());
//        List<ArticleManagementVo> articleManagementVos = BeanCopyUtils.copyBeanList(articleList, ArticleManagementVo.class);
//        pageVo.setRows(articleManagementVos);
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleForUpdate(Long id) {
        // 判断id是否为空
        if(id == null){
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        // 根据id查询文章内容
        Article article = getById(id);
        // 从redis中获取viewCount
        article.setViewCount(getArticleViewCountById(id).longValue());
        // 转化成vo（借用一下dto）
        UpdateArticleDto dto = BeanCopyUtils.copyBean(article, UpdateArticleDto.class);
        // 根据文章id查询标签
        List<Long> tags = articleTagService.list().stream().filter(articleTag -> articleTag.getArticleId().equals(id))
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());
        dto.setTags(tags);
        // 返回查询结果
        return ResponseResult.okResult(dto);
    }

    @Override
    @Transactional
    public ResponseResult updateArticle(UpdateArticleDto articleDto) {
        // 将articleDto中的数据拷贝到article中
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        //删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        // 存入文章
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long id) {
        // 判断id是否为空
        if(id == null){
            throw new SystemException(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 删除文章
        removeById(id);
        return null;
    }

    public Integer getArticleViewCountById(Long id){
        //获取redis中对应id的浏览量
        return redisCache.getCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString());
    }

    public void updateAllArticleViewCount(){
        // 查询博客信息
        List<Article> articleList = articleMapper.selectList(null);
        Map<String, Integer> viewCount = articleList.stream().
                collect(Collectors.toMap(article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()));
        // 删除redis中文章浏览量
        redisCache.deleteObject(SystemConstants.ARTICLE_VIEW_COUNT);
        // 存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT,viewCount);
    }
}
