package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "AdminArticleController", description = "Admin Article Related Apis")
@RequestMapping("/content/article")
public class AdminArticleController {
  @Autowired
  private ArticleService articleService;

  @ApiOperation(value = "Add Article")
  @PostMapping
  public ResponseEntity<ResponseResult> addArticle(@RequestBody AddArticleDto articleDto) {
    ResponseResult result = articleService.addArticle(articleDto);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<PageVo> searchArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
    if (pageNum == null || pageSize == null) {
      throw new IllegalArgumentException("Page Num and Page Size are Required!");
    }
    PageVo articlePageVo = articleService.searchArticleList(pageNum, pageSize, title, summary);
    return new ResponseEntity<>(articlePageVo, HttpStatus.OK);
  }

  @ApiOperation(value = "Get Article Details by ID for Update")
  @GetMapping("/{id}")
  public ResponseEntity<UpdateArticleDto> getArticleDetail(@PathVariable Long id) {
    UpdateArticleDto updateArticleDto = articleService.getArticleForUpdate(id);
    return new ResponseEntity<>(updateArticleDto, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Article")
  @PutMapping
  public ResponseEntity<ResponseResult> updateArticle(@RequestBody UpdateArticleDto articleDto) {
    ResponseResult result = articleService.updateArticle(articleDto);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @ApiOperation(value = "Delete Article by ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseResult> deleteArticle(@PathVariable Long id) {
    ResponseResult result = articleService.deleteArticle(id);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
