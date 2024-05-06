package com.wbw1537.controller;

import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            // 提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return adminLoginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return adminLoginService.logout();
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        // 获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        // 根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //List<String> roleKeyList = null;
        // 获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        // 封装数据放回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        // 获取当前登录的用户
        Long userId = SecurityUtils.getUserId();
        // 查询menu 结果是tree的形式
        List<Menu> menu = menuService.selectRouterTreeByUserId(userId);
        //封装数据返回
        RoutersVo routersVo = new RoutersVo(menu);
        return ResponseResult.okResult(routersVo);

    }
}
