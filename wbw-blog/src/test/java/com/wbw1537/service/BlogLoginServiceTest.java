package com.wbw1537.service;

import com.wbw1537.domain.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogLoginServiceTest {
  // This Test class contains tests for UserDetailsService
  private static final UserLoginDto USER_WITH_CORRECT_USERNAME_INVALID_PASSWORD = new UserLoginDto("sg", "21");
  private static final UserLoginDto USER_WITH_CORRECT_USERNAME_CORRECT_PASSWORD = new UserLoginDto("sg", "1234");
  private static final UserLoginDto NULL_USER = new UserLoginDto("123","123");
  @Autowired
  private BlogLoginService blogLoginService;
  @Test
  public void loginWithNullUserShouldReturnRuntimeException() {
    // Call the service
    blogLoginService.login(NULL_USER);


  }
}
