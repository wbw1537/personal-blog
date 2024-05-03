package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.service.ArticleService;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
public class ArticleControllerTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private ArticleService mockArticleService;

  private static final String ACCESS_ARTICLE_API_PATH = "/article/";
  private static final String ACCESS_HOT_ARTICLE_API_PATH = "/article/hotArticleList/";
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void addArticleShouldReturnResponseOfArticle() throws Exception {
    // Mock some data
    Article mockArticle = new Article(123L, 123);
    String json = objectMapper.writeValueAsString(mockArticle);
    ResponseResult response = ResponseResult.okResult(mockArticle);
    // Mock the service
    when(mockArticleService.addArticle(BeanCopyUtils.copyBean(mockArticle, AddArticleDto.class))).thenReturn(response);
    // Call the controller
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ACCESS_ARTICLE_API_PATH)
                    .param("article", json))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void hotArticleListShouldReturnResponseOfHotArticleList() throws Exception {
    // Mock the service
    when(mockArticleService.hotArticleList()).thenReturn(ResponseResult.okResult());
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ACCESS_HOT_ARTICLE_API_PATH))
            .andExpect(status().isOk())
            .andReturn();

  }

  @Test
  public void articleListShouldReturnResponseOfArticleList() throws Exception {
    Integer mockPageNum = 1;
    Integer mockPageSize = 10;
    Long mockCategoryId = 123L;
    ResponseResult response = ResponseResult.okResult();
    when(mockArticleService.articleList(mockPageNum, mockPageSize, mockCategoryId)).thenReturn(response);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ACCESS_ARTICLE_API_PATH+"articleList")
            .param("pageNum", mockPageNum.toString())
            .param("pageSize", mockPageSize.toString())
            .param("categoryId", mockCategoryId.toString()))
            .andExpect(status().isOk())
            .andReturn();
  }
  @Test
  public void getArticleDetailShouldReturnResponseOfArticle() throws Exception {
    Long mockArticleId = 66L;
    ResponseResult response = ResponseResult.okResult(new Article(mockArticleId,123));
    when(mockArticleService.getArticleDetail(mockArticleId)).thenReturn(response);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(ACCESS_ARTICLE_API_PATH+mockArticleId))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void updateArticleShouldReturnResponseOfArticle() throws Exception {
    Article mockArticle = new Article(123L, 123);
    String json = objectMapper.writeValueAsString(mockArticle);
    ResponseResult response = ResponseResult.okResult(mockArticle);
    when(mockArticleService.updateArticle(BeanCopyUtils.copyBean(mockArticle, UpdateArticleDto.class))).thenReturn(response);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ACCESS_ARTICLE_API_PATH)
            .param("article", json))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void updateViewCountShouldReturnResponseOfArticle() throws Exception {
    Long mockArticleId = 66L;
    ResponseResult response = ResponseResult.okResult(new Article(mockArticleId,123));
    when(mockArticleService.updateViewCount(mockArticleId)).thenReturn(response);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(ACCESS_ARTICLE_API_PATH+"updateViewCount/"+mockArticleId))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void deleteArticleShouldReturnResponseOfArticle() throws Exception {
    Long mockArticleId = 66L;
    ResponseResult response = ResponseResult.okResult(new Article(mockArticleId,123));
    when(mockArticleService.deleteArticle(mockArticleId)).thenReturn(response);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(ACCESS_ARTICLE_API_PATH+mockArticleId))
        .andExpect(status().isOk())
        .andReturn();
  }

}
