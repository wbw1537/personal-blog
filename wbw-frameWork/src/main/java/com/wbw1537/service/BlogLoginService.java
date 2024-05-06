package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.BlogUserLoginVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface BlogLoginService {
  ResponseEntity<BlogUserLoginVo> login(UserLoginDto user);

  ResponseEntity<ResponseResult> logout();
}
