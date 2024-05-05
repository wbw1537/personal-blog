package com.wbw1537.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.service.BlogLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BlogLoginControllerTest {
  private static final String LOGIN_API_PATH = "/login";
  private static final String LOGOUT_API_PATH = "/logout";

  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private BlogLoginService mockBlogLoginService;
  private ObjectMapper objectMapper;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void loginWithoutUserNameShouldReturnInternalException() throws Exception {
    // Mock some data
    UserLoginDto userLoginDto = new UserLoginDto("", "password");
    String json = objectMapper.writeValueAsString(userLoginDto);
    // Call the controller
    mockMvc.perform(MockMvcRequestBuilders.post(LOGIN_API_PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
        .andExpect(status().isInternalServerError());
  }
}
