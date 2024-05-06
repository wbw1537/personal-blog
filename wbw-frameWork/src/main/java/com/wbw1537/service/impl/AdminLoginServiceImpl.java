package com.wbw1537.service.impl;

import com.mysql.cj.log.Log;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.LoginUser;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.BlogUserLoginVo;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.service.AdminLoginService;
import com.wbw1537.service.BlogLoginService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.JwtUtil;
import com.wbw1537.utils.RedisCache;
import com.wbw1537.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.wbw1537.constants.SystemConstants.BLOG_LOGIN;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
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
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        // 获取当前登录的用户id
        Long id = SecurityUtils.getUserId();
        // 删除redis中对应的token
        redisCache.deleteObject(SystemConstants.ADMIN_LOGIN + id);
        return ResponseResult.okResult();
    }
}
