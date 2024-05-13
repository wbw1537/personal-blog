package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.service.UserService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WbwBlogApplication.class)
public class UserControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private UserService mockUserService;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void getUserInfoShouldReturnOkResult() throws Exception {
    User user = BlogTestHelper.NORMAL_USER;
    UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
    ResponseEntity response = ResponseEntity.ok(userInfoVo);
    when(mockUserService.userInfo()).thenReturn(response);
    mockMvc.perform(MockMvcRequestBuilders.get(BlogTestHelper.USER_INFO_API_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void updateUserInfoShouldReturnOkResult() throws Exception {
    User user = BlogTestHelper.NORMAL_USER;
    UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    when(mockUserService.updateUserInfo(any())).thenReturn(response);
    String json = objectMapper.writeValueAsString(userInfoVo);
    mockMvc.perform(MockMvcRequestBuilders.put(BlogTestHelper.USER_INFO_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void updateUserInfoWithNullShouldReturnIllegalArgumentException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.put(BlogTestHelper.USER_INFO_API_PATH))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void registerShouldReturnOkResult() throws Exception {
    User user = BlogTestHelper.NORMAL_USER;
    ResponseEntity response = new ResponseEntity(HttpStatus.OK);
    when(mockUserService.register(any())).thenReturn(response);
    String json = objectMapper.writeValueAsString(user);
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.REGISTER_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void registerWithNullShouldReturnIllegalArgumentException() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.REGISTER_API_PATH))
        .andExpect(status().is4xxClientError());
  }
}
