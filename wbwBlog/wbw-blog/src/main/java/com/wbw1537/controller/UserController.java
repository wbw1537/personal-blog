package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
}
