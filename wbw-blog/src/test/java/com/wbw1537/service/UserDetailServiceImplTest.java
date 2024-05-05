package com.wbw1537.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wbw1537.BlogTestHelper;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.domain.entity.User;
import com.wbw1537.mapper.MenuMapper;
import com.wbw1537.mapper.UserMapper;
import com.wbw1537.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailServiceImplTest {
  @Resource
  UserDetailsServiceImpl userDetailService;

  @MockBean
  private UserMapper mockUserMapper;
  @MockBean
  private MenuMapper mockMenuMapper;
  @BeforeEach
  public void setUp() {
  }

  @Test
  public void loadNotExistingUserShouldThrowBadCredentialsException() {
    String mockUsername = "123";
    when(mockUserMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(null);
    assertThrows(BadCredentialsException.class, () -> {
      userDetailService.loadUserByUsername(mockUsername);
    });
  }

  @Test
  public void loadExistingNormalUserShouldReturnNormalLoginUser() {
    User mockUser = BlogTestHelper.normalUser;
    String mockUsername = mockUser.getUserName();
    when(mockUserMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(mockUser);
    LoginUser loginUser = (LoginUser) userDetailService.loadUserByUsername(mockUsername);
    assertEquals(loginUser.getUser(), mockUser);
  }

  @Test
  public void loadExistingAdminUserShouldReturnAdminLoginUser() {
    User mockUser = BlogTestHelper.adminUser;
    String mockUsername = mockUser.getUserName();
    when(mockUserMapper.selectOne(any(LambdaQueryWrapper.class))).thenReturn(mockUser);
    when(mockMenuMapper.selectPermsByUserId(mockUser.getId())).thenReturn(new ArrayList<>());
    LoginUser loginUser = (LoginUser) userDetailService.loadUserByUsername(mockUsername);
    assertEquals(loginUser.getUser(), mockUser);
  }
}
