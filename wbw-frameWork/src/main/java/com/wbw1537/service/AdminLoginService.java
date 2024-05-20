package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.AdminUserInfoVo;
import com.wbw1537.domain.vo.RoutersVo;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AdminLoginService {
    Map<String,String> login(UserLoginDto user);
    ResponseResult logout();
    AdminUserInfoVo getInfo();
    RoutersVo getRouters();
}
