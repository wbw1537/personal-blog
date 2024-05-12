package com.wbw1537.service;

import com.wbw1537.BlogTestHelper;
import com.wbw1537.WbwBlogApplication;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.utils.RedisCache;
import com.wbw1537.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WbwBlogApplication.class)
public class BlogLoginServiceTest {
  @Autowired
  private BlogLoginService blogLoginService;

  @MockBean
  private AuthenticationManager mockAuthenticationManager;
  @MockBean
  private RedisCache mockRedisCache;
  @MockBean
  private Authentication mockAuthenticator;

  @Test
  public void loginWithUserCanNotPassAuthShouldReturnBadCredentialsException() {
    UserLoginDto userLoginDto = BlogTestHelper.USER_LOGIN_DTO;
    when(mockAuthenticationManager
            .authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(null);
    assertThrows(BadCredentialsException.class, () -> {
      blogLoginService.login(userLoginDto);
    });
  }

  @Test
  public void loginWithUserCanPassAuthShouldReturnResponseVo(){
    UserLoginDto userLoginDto = BlogTestHelper.USER_LOGIN_DTO;
    when(mockAuthenticationManager
            .authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(mockAuthenticator);
    LoginUser mockLoginUser = BlogTestHelper.LOGIN_USER;
    when(mockAuthenticator.getPrincipal()).thenReturn(mockLoginUser);
    doNothing().when(mockRedisCache).setCacheObject(anyString(), any());
    ResponseEntity response = blogLoginService.login(userLoginDto);
    assert response.getStatusCode().is2xxSuccessful();
  }

  @Test
  public void logoutShouldReturnResponseVo(){
    MockedStatic<SecurityUtils> securityUtils = mockStatic(SecurityUtils.class);
    securityUtils.when(()->SecurityUtils.getUserId()).thenReturn(1L);
    when(mockRedisCache.deleteObject(anyString())).thenReturn(true);
    ResponseEntity response = blogLoginService.logout();
    assert response.getStatusCode().is2xxSuccessful();
  }
}
