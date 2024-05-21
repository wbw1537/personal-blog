package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.ArticleDetailVo;
import com.wbw1537.domain.vo.HotArticleVo;
import com.wbw1537.domain.vo.PageVo;

import java.util.List;

public interface ArticleService extends IService<Article> {
    List<HotArticleVo> hotArticleList();

    PageVo articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ArticleDetailVo getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addArticle(AddArticleDto articleDto);

    PageVo searchArticleList(Integer pageNum, Integer pageSize, String title, String summary);

    UpdateArticleDto getArticleForUpdate(Long id);

    ResponseResult updateArticle(UpdateArticleDto articleDto);

    ResponseResult deleteArticle(Long id);
}
