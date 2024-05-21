package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.ArticleDetailVo;
import com.wbw1537.domain.vo.HotArticleVo;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.service.ArticleService;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwBlogApplication.class)
public class ArticleControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private ArticleService mockArticleService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void hotArticleListShouldReturnResponseOfHotArticleList() throws Exception {
    List<Article> articleList = BlogTestHelper.ARTICLE_LIST;
    List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
    when(mockArticleService.hotArticleList()).thenReturn(hotArticleVoList);
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.HOT_ARTICLE_API_PATH))
            .andExpect(status().isOk())
            .andExpect(result -> hotArticleVoList.equals(result.getResponse()))
            .andReturn();
  }

  @Test
  public void articleListWithoutPageNumAndPageSizeShouldThrowException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.ARTICLE_LIST_API_PATH))
            .andExpect(status().isBadRequest())
            .andExpect(result -> result.getResolvedException().getClass().equals(IllegalArgumentException.class))
            .andReturn();
  }

  @Test
  public void articleListShouldReturnResponseOfArticleList() throws Exception {
    Integer mockPageNum = 1;
    Integer mockPageSize = 10;
    Long mockCategoryId = 123L;
    PageVo articleListPageVo = new PageVo(BlogTestHelper.ARTICLE_LIST, mockPageSize.longValue());
    when(mockArticleService.articleList(mockPageNum, mockPageSize, mockCategoryId))
            .thenReturn(articleListPageVo);
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.ARTICLE_LIST_API_PATH)
                    .param("pageNum", mockPageNum.toString())
                    .param("pageSize", mockPageSize.toString())
                    .param("categoryId", mockCategoryId.toString()))
            .andExpect(status().isOk())
            .andExpect(result -> articleListPageVo.equals(result.getResponse()))
            .andReturn();
  }

  @Test
  public void getArticleDetailShouldReturnResponseOfArticle() throws Exception {
    Article article = BlogTestHelper.ARTICLE;
    ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article,ArticleDetailVo.class);
    when(mockArticleService.getArticleDetail(articleDetailVo.getId()))
            .thenReturn(articleDetailVo);
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.ARTICLE_API_PATH + "/" + articleDetailVo.getId()))
            .andExpect(status().isOk())
            .andExpect(result -> articleDetailVo.equals(result.getResponse()))
            .andReturn();
  }

  @Test
  public void updateViewCountShouldReturnResponseOfArticle() throws Exception {
    Long mockArticleId = 66L;
    ResponseResult response = ResponseResult.okResult();
    when(mockArticleService.updateViewCount(mockArticleId)).thenReturn(response);
    mockMvc.perform(MockMvcRequestBuilders.put(BlogTestHelper.UPDATE_VIEW_COUNT_API_PATH + "/" + mockArticleId))
            .andExpect(status().isOk())
            .andReturn();
  }
}
