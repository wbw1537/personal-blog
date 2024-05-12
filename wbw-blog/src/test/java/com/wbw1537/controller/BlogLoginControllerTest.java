package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.vo.BlogUserLoginVo;
import com.wbw1537.service.BlogLoginService;
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
public class BlogLoginControllerTest {


  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private BlogLoginService mockBlogLoginService;
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void loginWithoutUserNameShouldReturnIllegalArgumentException() throws Exception {
    // Mock some data
    UserLoginDto userLoginDto = BlogTestHelper.USER_LOGIN_DTO_WITHOUT_USERNAME;
    String json = objectMapper.writeValueAsString(userLoginDto);
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.LOGIN_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().is4xxClientError());
  }
  @Test
  public void loginWithUserNameShouldReturnResponseOfUser() throws Exception {
    // Mock some data
    UserLoginDto userLoginDto = BlogTestHelper.USER_LOGIN_DTO;
    String json = objectMapper.writeValueAsString(userLoginDto);
    BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo();
    // Mock the service
    when(mockBlogLoginService.login(userLoginDto)).thenReturn(new ResponseEntity<>(blogUserLoginVo, HttpStatus.OK));
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.LOGIN_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().isOk());
  }
  @Test
  public void logoutShouldReturnResponseOfUser() throws Exception {
    // Mock the service
    when(mockBlogLoginService.logout()).thenReturn(new ResponseEntity<>(ResponseResult.okResult(), HttpStatus.OK));
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(BlogTestHelper.LOGOUT_API_PATH))
        .andExpect(status().isOk());
  }
}
