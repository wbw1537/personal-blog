package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.ArticleDetailVo;
import com.wbw1537.domain.vo.HotArticleVo;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "ArticleController", description = "Article Related APIs")
@RequestMapping("/article")
public class ArticleController {
  @Autowired
  private ArticleService articleService;

  @ApiOperation(value = "Get Hot Articles")
  @GetMapping("/hotArticleList")
  public ResponseEntity<List<HotArticleVo>> hotArticleList() {
    List<HotArticleVo> hotArticleVoList = articleService.hotArticleList();
    return new ResponseEntity<>(hotArticleVoList, HttpStatus.OK);
  }

  @ApiOperation(value = "Get Article List")
  @GetMapping("/articleList")
  public ResponseEntity<PageVo> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
    if (pageNum == null || pageSize == null) {
      throw new IllegalArgumentException("Page Num and Page Size are Required!");
    }
    PageVo articlePageVo = articleService.articleList(pageNum, pageSize, categoryId);
    return new ResponseEntity<>(articlePageVo, HttpStatus.OK);
  }

  @ApiOperation(value = "Get Article Details by ID")
  @GetMapping("/{id}")
  public ResponseEntity<ArticleDetailVo> getArticleDetail(@PathVariable("id") Long id) {
    ArticleDetailVo articleDetailVo = articleService.getArticleDetail(id);
    return new ResponseEntity<>(articleDetailVo, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Article View Count by ID")
  @PutMapping("/updateViewCount/{id}")
  public ResponseEntity updateViewCount(@PathVariable Long id) {
    ResponseResult result = articleService.updateViewCount(id);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
