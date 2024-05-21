package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.AddArticleDto;
import com.wbw1537.domain.dto.UpdateArticleDto;
import com.wbw1537.domain.entity.Article;
import com.wbw1537.domain.vo.ArticleDetailVo;
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
import com.wbw1537.AdminBlogTestHelper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwAdminApplication.class)
public class AdminArticleControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  ArticleService mockArticleService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void addArticleShouldReturnOkResult() throws Exception {
    // Mock some data
    AddArticleDto articleDto = AdminBlogTestHelper.ADD_ARTICLE_DTO;
    String json = objectMapper.writeValueAsString(articleDto);
    ResponseResult response = ResponseResult.okResult();
    // Mock the service
    when(mockArticleService.addArticle(articleDto))
            .thenReturn(response);
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.ADMIN_ARTICLE_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void searchArticleListWithoutPageNumAndPageSizeShouldThrowIllegalArgumentException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.SEARCH_ARTICLE_LIST_API_PATH))
            .andExpect(status().isBadRequest())
            .andExpect(result -> result.getResolvedException().getClass().equals(IllegalArgumentException.class))
            .andReturn();
  }

  @Test
  public void searchArticleListShouldReturnOkResult() throws Exception {
    Integer mockPageNum = 1;
    Integer mockPageSize = 10;
    PageVo articleListPageVo = new PageVo(AdminBlogTestHelper.ARTICLE_LIST, mockPageSize.longValue());
    when(mockArticleService.searchArticleList(any(),any(),any(),any()))
            .thenReturn(articleListPageVo);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.SEARCH_ARTICLE_LIST_API_PATH)
                    .param("pageNum", mockPageNum.toString())
                    .param("pageSize", mockPageSize.toString()))
            .andExpect(status().isOk())
            .andExpect(result -> articleListPageVo.equals(result.getResponse()))
            .andReturn();
  }
  @Test
  public void getArticleDetailShouldReturnResponseOfArticle() throws Exception {
    Article article = AdminBlogTestHelper.ARTICLE;
    UpdateArticleDto articleDetailVo = BeanCopyUtils.copyBean(article,UpdateArticleDto.class);
    when(mockArticleService.getArticleForUpdate(article.getId())).thenReturn(articleDetailVo);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.ADMIN_ARTICLE_API_PATH + "/" + article.getId()))
            .andExpect(status().isOk())
            .andExpect(result -> articleDetailVo.equals(result.getResponse()))
            .andReturn();
  }

  @Test
  public void updateArticleShouldReturnResponseOfArticle() throws Exception {
    UpdateArticleDto articleDto = AdminBlogTestHelper.UPDATE_ARTICLE_DTO;
    String json = objectMapper.writeValueAsString(articleDto);
    ResponseResult response = ResponseResult.okResult();
    when(mockArticleService.updateArticle(articleDto)).thenReturn(response);
    mockMvc.perform(MockMvcRequestBuilders.put(AdminBlogTestHelper.ADMIN_ARTICLE_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void deleteArticleShouldReturnResponseOfArticle() throws Exception {
    Long mockArticleId = 66L;
    ResponseResult response = ResponseResult.okResult();
    when(mockArticleService.deleteArticle(mockArticleId)).thenReturn(response);
    mockMvc.perform(MockMvcRequestBuilders.delete(AdminBlogTestHelper.ADMIN_ARTICLE_API_PATH + "/" + mockArticleId))
            .andExpect(status().isOk())
            .andReturn();
  }
}
