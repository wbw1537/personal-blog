package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.entity.Comment;
import com.wbw1537.domain.vo.CommentVo;
import com.wbw1537.service.CommentService;
import com.wbw1537.utils.BeanCopyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwBlogApplication.class)
public class CommentControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;
  @MockBean
  private CommentService mockCommentService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void requestCommentListWithoutParamShouldReturnBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.COMMENT_LIST_API_PATH))
        .andExpect(status().isBadRequest())
        .andReturn();
  }
  @Test
  public void commentListShouldReturnResponseOfCommentList() throws Exception {
    Comment comment = BlogTestHelper.ARTICLE_COMMENT;
    CommentVo commentVo = BeanCopyUtils.copyBean(comment, CommentVo.class);
    Long mockArticleId = comment.getArticleId();
    Integer mockPageNum = 1;
    Integer mockPageSize = 10;
    when(mockCommentService.commentList(SystemConstants.ARTICLE_COMMENT, mockArticleId, mockPageNum, mockPageSize))
        .thenReturn(new ResponseEntity<>(commentVo, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.COMMENT_LIST_API_PATH)
                    .param("commentType", SystemConstants.ARTICLE_COMMENT)
                    .param("articleId", mockArticleId.toString())
                    .param("pageNum", mockPageNum.toString())
                    .param("pageSize", mockPageSize.toString()))
        .andExpect(status().isOk())
        .andReturn();
  }
  @Test
  public void requestLinkCommentListWithoutParamShouldReturnBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.LINK_COMMENT_LIST_API_PATH))
            .andExpect(status().isBadRequest())
            .andReturn();
  }
  @Test
  public void requestLinkCommentListShouldReturnResponseOfCommentList() throws Exception {
    Comment comment = BlogTestHelper.LINK_COMMENT;
    CommentVo commentVo = BeanCopyUtils.copyBean(comment, CommentVo.class);
    Integer mockPageNum = 1;
    Integer mockPageSize = 10;
    when(mockCommentService.commentList(SystemConstants.LINK_COMMENT, null, mockPageNum, mockPageSize))
            .thenReturn(new ResponseEntity<>(commentVo, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.LINK_COMMENT_LIST_API_PATH)
            .param("commentType", SystemConstants.LINK_COMMENT)
            .param("pageNum", mockPageNum.toString())
            .param("pageSize", mockPageSize.toString()))
            .andExpect(status().isOk())
            .andReturn();
  }
  @Test
  public void addCommentWithoutParamShouldReturnBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.ADD_COMMENT_API_PATH))
        .andExpect(status().isBadRequest())
        .andReturn();
  }
  @Test
  public void addCommentShouldReturnResponseOfComment() throws Exception {
    Comment comment = BlogTestHelper.ARTICLE_COMMENT;
    String json = objectMapper.writeValueAsString(comment);
    when(mockCommentService.addComment(comment)).thenReturn(new ResponseEntity<>(comment, HttpStatus.OK));
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.ADD_COMMENT_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().isOk())
        .andReturn();
  }

}
