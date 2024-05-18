package com.wbw1537.service.impl;

import com.mysql.cj.log.Log;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserLoginDto;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.domain.entity.Menu;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.AdminUserInfoVo;
import com.wbw1537.domain.vo.BlogUserLoginVo;
import com.wbw1537.domain.vo.RoutersVo;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.service.AdminLoginService;
import com.wbw1537.service.BlogLoginService;
import com.wbw1537.service.MenuService;
import com.wbw1537.service.RoleService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.JwtUtil;
import com.wbw1537.utils.RedisCache;
import com.wbw1537.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.wbw1537.constants.SystemConstants.BLOG_LOGIN;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @Override
    public ResponseEntity login(UserLoginDto user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        // 调用UserDetailsService
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否认证通过
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把用户信息存入redis
        redisCache.setCacheObject(SystemConstants.ADMIN_LOGIN + userId, loginUser);

//        // 将user bean拷贝为userInfoVo
//        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
//        // 将token和userInfo封装返回给前端
//        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userInfoVo);
        //将token封装返回
        Map<String,String>map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseEntity(map, HttpStatus.OK);
    }
    @Override
    public ResponseEntity logout() {
        // 获取当前登录的用户id
        Long id = SecurityUtils.getUserId();
        // 删除redis中对应的token
        redisCache.deleteObject(SystemConstants.ADMIN_LOGIN + id);
        return new ResponseEntity<>(ResponseResult.okResult(), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<AdminUserInfoVo> getInfo() {
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
        return new ResponseEntity<>(adminUserInfoVo, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<RoutersVo> getRouters() {
        // 获取当前登录的用户
        Long userId = SecurityUtils.getUserId();
        // 查询menu 结果是tree的形式
        List<Menu> menu = menuService.selectRouterTreeByUserId(userId);
        //封装数据返回
        RoutersVo routersVo = new RoutersVo(menu);
        return new ResponseEntity<>(routersVo, HttpStatus.OK);
    }

}
