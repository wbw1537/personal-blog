package com.wbw1537.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.entity.Category;
import com.wbw1537.domain.vo.CategoryVo;
import com.wbw1537.mapper.ArticleMapper;
import com.wbw1537.mapper.CategoryMapper;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WbwBlogApplication.class)
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
    List<CategoryVo> sourceCategoryVos = BeanCopyUtils.copyBeanList(categories,CategoryVo.class);
    // define responses for the mock service
    when(mockArticleMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(articles);
    when(mockCategoryMapper.selectBatchIds(anySet())).thenReturn(categories);
    // verify status codes and response
    List<CategoryVo> targetCategoryVos = categoryService.getCategoryList();
    assert sourceCategoryVos.equals(targetCategoryVos);
  }

  @Test
  public void listAllCategoryShouldReturnOkResponse() {
    // create test data
    List<Category> categories = BlogTestHelper.CATEGORY_LIST;
    List<CategoryVo> sourceCategoryVos = BeanCopyUtils.copyBeanList(categories,CategoryVo.class);
    // define responses for the mock service
    when(mockCategoryMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(categories);
    // verify status codes and response
    List<CategoryVo> targetCategoryVos = categoryService.listAllCategory();
    assert sourceCategoryVos.equals(targetCategoryVos);
  }
}
