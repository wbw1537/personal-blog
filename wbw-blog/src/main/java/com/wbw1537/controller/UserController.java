package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/userInfo")
    public ResponseEntity userInfo(){
        return userService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseEntity updateUserInfo(@RequestBody UserInfoDto user){
        if (user == null){
            throw new IllegalArgumentException("User info is required in updating user info");
        }
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if (user == null){
            throw new IllegalArgumentException("User info is required in register");
        }
        return userService.register(user);
    }
}
