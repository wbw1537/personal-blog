package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.AdminBlogTestHelper;
import com.wbw1537.WbwAdminApplication;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.vo.BlogUserLoginVo;
import com.wbw1537.service.AdminLoginService;
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

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = WbwAdminApplication.class)
public class AdminLoginControllerTest {
  @Autowired
  private WebApplicationContext webApplicationContext;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @MockBean
  private AdminLoginService mockAdminLoginService;
  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void loginWithoutUserNameShouldReturnIllegalArgumentException() throws Exception {
    UserLoginDto userLoginDto = AdminBlogTestHelper.USER_LOGIN_DTO_WITHOUT_USERNAME;
    String json = objectMapper.writeValueAsString(userLoginDto);
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.ADMIN_LOGIN_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().is4xxClientError())
            .andExpect(result -> result.getResolvedException().equals(new IllegalArgumentException("Username is required")));
  }
  @Test
  public void loginWithUserNameShouldReturnResponseOfUser() throws Exception {
    UserLoginDto userLoginDto = AdminBlogTestHelper.USER_LOGIN_DTO;
    String json = objectMapper.writeValueAsString(userLoginDto);
    Map<String,String> tokenMap = new HashMap<>();
    tokenMap.put("token","jwt");
    when(mockAdminLoginService.login(userLoginDto))
            .thenReturn(tokenMap);
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.ADMIN_LOGIN_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk());
  }
  @Test
  public void logoutShouldReturnResponseOfUser() throws Exception {
    // Mock the service
    when(mockAdminLoginService.logout())
            .thenReturn(ResponseResult.okResult());
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(AdminBlogTestHelper.LOGOUT_API_PATH))
            .andExpect(status().isOk());
  }
  @Test
  public void getInfoShouldReturnAdminUserInfoVo() throws Exception {
    when(mockAdminLoginService.getInfo())
            .thenReturn(AdminBlogTestHelper.ADMIN_USER_INFO_VO);
    mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.GET_INFO_API_PATH))
            .andExpect(status().isOk());
  }
  @Test
  public void getRoutersShouldReturnRoutersVo() throws Exception {
     when(mockAdminLoginService.getRouters())
             .thenReturn(AdminBlogTestHelper.ROUTERS_VO);
     mockMvc.perform(MockMvcRequestBuilders.get(AdminBlogTestHelper.GET_ROUTERS_API_PATH))
             .andExpect(status().isOk());
  }
}
