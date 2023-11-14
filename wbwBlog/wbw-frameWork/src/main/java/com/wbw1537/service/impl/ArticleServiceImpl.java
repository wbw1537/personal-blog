package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.ArticleTag;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.ArticleDetailVo;
import com.wbw1537.domain.vo.ArticleListVo;
import com.wbw1537.domain.vo.HotArticleVo;
import com.wbw1537.domain.vo.PageVo;
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
    //@Transactional
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
