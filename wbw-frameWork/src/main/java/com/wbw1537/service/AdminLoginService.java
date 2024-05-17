package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.AdminUserInfoVo;
import com.wbw1537.domain.vo.RoutersVo;
import org.springframework.http.ResponseEntity;

public interface AdminLoginService {
    ResponseEntity login(UserLoginDto user);
    ResponseEntity logout();
    ResponseEntity<AdminUserInfoVo> getInfo();
    ResponseEntity<RoutersVo> getRouters();
}
