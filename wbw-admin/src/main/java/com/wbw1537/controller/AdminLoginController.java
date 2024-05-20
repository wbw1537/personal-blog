package com.wbw1537.controller;

import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.AdminUserInfoVo;
import com.wbw1537.domain.vo.MenuVo;
import com.wbw1537.domain.vo.RoutersVo;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.AdminLoginService;
import com.wbw1537.service.MenuService;
import com.wbw1537.service.RoleService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.RedisCache;
import com.wbw1537.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "AdminLoginController", description = "Admin Login APIs")
@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "Admin Login")
    @PostMapping("/user/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody UserLoginDto user){
        if(!StringUtils.hasText(user.getUsername())){
            throw new IllegalArgumentException("Username is required");
        }
        Map<String,String> token = adminLoginService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @ApiOperation(value = "Admin Logout")
    @PostMapping("/user/logout")
    public ResponseEntity<ResponseResult> logout(){
        ResponseResult result = adminLoginService.logout();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Admin Info")
    @GetMapping("/getInfo")
    public ResponseEntity<AdminUserInfoVo> getInfo(){
        AdminUserInfoVo adminUserInfoVo = adminLoginService.getInfo();
        return new ResponseEntity<>(adminUserInfoVo, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Routers")
    @GetMapping("/getRouters")
    public ResponseEntity<RoutersVo> getRouters(){
        RoutersVo routersVo = adminLoginService.getRouters();
        return new ResponseEntity<>(routersVo, HttpStatus.OK);
    }
}
