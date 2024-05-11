package com.wbw1537.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {

  @Autowired
  private CategoryService categoryService;

  @MockBean
  private ArticleMapper mockArticleMapper;
  @MockBean
  private CategoryMapper mockCategoryMapper;

  @Test
  public void getCategoryListShouldReturnOkResponse() {
    // create test data
    List<Article> articles = BlogTestHelper.ARTICLE_LIST;
    List<Category> categories = BlogTestHelper.CATEGORY_LIST;
    // define responses for the mock service
    when(mockArticleMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(articles);
    when(mockCategoryMapper.selectBatchIds(anySet())).thenReturn(categories);
    // verify status codes and response
    ResponseEntity result = categoryService.getCategoryList();
    assert result.getStatusCode().is2xxSuccessful();
  }

  @Test
  public void listAllCategoryShouldReturnOkResponse() {
    // create test data
    List<Category> categories = BlogTestHelper.CATEGORY_LIST;
    // define responses for the mock service
    when(mockCategoryMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(categories);
    // verify status codes and response
    ResponseEntity result = categoryService.listAllCategory();
    assert result.getStatusCode().is2xxSuccessful();
  }
}
