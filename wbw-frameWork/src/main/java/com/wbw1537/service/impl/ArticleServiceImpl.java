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
    public List<HotArticleVo> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus,ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        // 获取redis中对应id的浏览量并赋值
        for(Article article : articles){
            article.setViewCount(getArticleViewCountById(article.getId()).longValue());
        }
        return BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
    }

    @Override
    public PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        lambdaQueryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        lambdaQueryWrapper.orderByDesc(Article::getCreateTime);
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        // 查询categoryName（分类名称）
        List<Article> articles = page.getRecords();
        for (Article article : articles){
            Category category = categoryMapper.selectById(article.getCategoryId());
            article.setCategoryName(category.getName());
            //获取redis中对应id的浏览量并赋值
            article.setViewCount(getArticleViewCountById(article.getId()).longValue());
        }
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        return new PageVo(articleListVos, page.getTotal());
    }

    @Override
    public ArticleDetailVo getArticleDetail(Long id) {
        Article article = getById(id);
        article.setViewCount(getArticleViewCountById(id).longValue());
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);
        // 根据分类id查询分类名称
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return articleDetailVo;
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT,id.toString(), 1L);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addArticle(AddArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        // 添加博客-标签表的关联关系
        articleTagService.saveBatch(articleTags);
        updateAllArticleViewCount();
        return ResponseResult.okResult();
    }

    @Override
    public PageVo searchArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(title), Article::getTitle, title);
        queryWrapper.like(StringUtils.hasText(summary),Article::getSummary,summary);
        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        List<Article> articleList = page.getRecords();
        List<ArticleManagementVo> articleManagementVos = BeanCopyUtils.copyBeanList(articleList, ArticleManagementVo.class);
        return new PageVo(articleManagementVos,page.getTotal());
    }

    @Override
    public UpdateArticleDto getArticleForUpdate(Long id) {
        Article article = getById(id);
        article.setViewCount(getArticleViewCountById(id).longValue());
        UpdateArticleDto dto = BeanCopyUtils.copyBean(article, UpdateArticleDto.class);
        // 根据文章id查询标签
        List<Long> tags = articleTagService.list().stream().filter(articleTag -> articleTag.getArticleId().equals(id))
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());
        dto.setTags(tags);
        return dto;
    }

    @Override
    @Transactional
    public ResponseResult updateArticle(UpdateArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        // 删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        // 添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long id) {
        // 删除文章
        removeById(id);
        return ResponseResult.okResult();
    }

    public Integer getArticleViewCountById(Long id){
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
