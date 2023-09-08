package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成responseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //要求为正式文章
        queryWrapper.eq(Article::getStatus,0);
        //降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //限制数量（10条）
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        return ResponseResult.okResult(articles);
    }
}
