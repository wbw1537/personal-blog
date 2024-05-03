package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "ArticleController", description = "Article Related APIs")
@RequestMapping("/article")
public class ArticleController {
  @Autowired
  private ArticleService articleService;

  @ApiOperation(value = "Add Article")
  @PostMapping
  public ResponseResult addArticle(@RequestBody AddArticleDto articleDto) {
    return articleService.addArticle(articleDto);
  }

  @ApiOperation(value = "Get Hot Articles")
  @GetMapping("/hotArticleList")
  public ResponseResult hotArticleList() {
    return articleService.hotArticleList();
  }

  @ApiOperation(value = "Get Article List")
  @GetMapping("/articleList")
  public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
    return articleService.articleList(pageNum, pageSize, categoryId);
  }

  @ApiOperation(value = "Get Article Details by ID")
  @GetMapping("/{id}")
  public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
    return articleService.getArticleDetail(id);
  }

  @ApiOperation(value = "Update Article")
  @PutMapping
  public ResponseResult updateArticle(@RequestBody UpdateArticleDto articleDto) {
    return articleService.updateArticle(articleDto);
  }

  @ApiOperation(value = "Update Article View Count by ID")
  @PutMapping("/updateViewCount/{id}")
  public ResponseResult updateViewCount(@PathVariable Long id) {
    return articleService.updateViewCount(id);
  }

  @ApiOperation(value = "Delete Article by ID")
  @DeleteMapping("/{id}")
  public ResponseResult deleteArticle(@PathVariable Long id) {
    return articleService.deleteArticle(id);
  }

}
