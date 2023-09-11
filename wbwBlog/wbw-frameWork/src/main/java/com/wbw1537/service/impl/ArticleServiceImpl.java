package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.ArticleListVo;
import com.wbw1537.domain.vo.HotArticleVo;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.mapper.CategoryMapper;
import com.wbw1537.service.ArticleService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.wbw1537.constants.SystemConstants.ARTICLE_STATUS_NORMAL;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    CategoryMapper categoryMapper;

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
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        //查询categoryName（分类名称）
        /*
        //采用for循环查询
        List<Article> articles = page.getRecords();
        //用categoryID查询categoryName
        for (Article article : articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
         */
        //采用stream流查询
        List<Article> articles = page.getRecords();
        articles.stream()
                .map(article -> article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos, page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
