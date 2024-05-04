package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "BlogLoginController", description = "Blog Login APIs")
@RestController
public class BlogLoginController {

    @Autowired
    private BlogLoginService blogLoginService;

    @ApiOperation(value = "User Login")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto user){
        if(!StringUtils.hasText(user.getUserName())){
            // Username is required
            throw new IllegalArgumentException("Username is required");
        }
      return blogLoginService.login(user);
    }

    @ApiOperation(value = "User Logout")
    @PostMapping("/logout")
    public ResponseEntity<ResponseResult> logout(){
        return blogLoginService.logout();
    }
}
