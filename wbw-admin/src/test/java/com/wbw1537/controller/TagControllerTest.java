package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.AdminBlogTestHelper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.TagDto;
import com.wbw1537.service.TagService;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwAdminApplication.class)
public class TagControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private TagService mockTagService;
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void searchTagWithoutParamShouldReturnBadRequest() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.TAG_LIST_API_PATH))
            .andExpect(result -> assertTrue(result.getResolvedException()
                    instanceof IllegalArgumentException));
  }
  @Test
  public void searchTagShouldReturnOkResult() throws Exception {
    when(mockTagService.searchTag(any(), any(), any()))
            .thenReturn(AdminBlogTestHelper.SEARCH_TAG_RESULT);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.TAG_LIST_API_PATH)
                    .param("pageNum", "1")
                    .param("pageSize", "10")
                    .param("id", "1")
                    .param("name", "tag")
                    .param("remark", "remark")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  @Test
  public void listAllTagsShouldReturnOkResult() throws Exception {
    when(mockTagService.listAllTags())
            .thenReturn(AdminBlogTestHelper.TAG_VO_LIST);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.TAG_LIST_ALL_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  @Test
  public void addTagShouldReturnOkResult() throws Exception {
    TagDto tagDto = AdminBlogTestHelper.TAG_DTO;
    String json = objectMapper.writeValueAsString(tagDto);
    when(mockTagService.addTag(any()))
            .thenReturn(ResponseResult.okResult());
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.TAG_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk());
  }
  @Test
  public void deleteTagShouldReturnOkResult() throws Exception {
    int id = 1;
    when(mockTagService.deleteTag(any()))
            .thenReturn(ResponseResult.okResult());
    mockMvc.perform(MockMvcRequestBuilders.delete(AdminBlogTestHelper.TAG_API_PATH + "/" + id)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  @Test
  public void getTagShouldReturnOkResult() throws Exception {
    int id = 1;
    when(mockTagService.getTag(any()))
            .thenReturn(AdminBlogTestHelper.TAG_DTO);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.TAG_API_PATH + "/" + id)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
  @Test
  public void updateTagShouldReturnOkResult() throws Exception {
    TagDto tagDto = AdminBlogTestHelper.TAG_DTO;
    String json = objectMapper.writeValueAsString(tagDto);
    when(mockTagService.updateTag(any()))
            .thenReturn(ResponseResult.okResult());
    mockMvc.perform(MockMvcRequestBuilders.put(AdminBlogTestHelper.TAG_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk());
  }
}
