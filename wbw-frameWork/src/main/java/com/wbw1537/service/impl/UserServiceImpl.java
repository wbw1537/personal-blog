package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.mapper.UserMapper;
import com.wbw1537.service.UserService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-11-02 22:58:13
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据id查询用户信息
        User user = getById(userId);
        // 封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return new ResponseEntity<>(userInfoVo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity updateUserInfo(UserInfoDto user) {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        if(userId == user.getId()) {
            // 根据id更新用户信息
            User user1 = BeanCopyUtils.copyBean(user, User.class);
            updateById(user1);
        } else {
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        return new ResponseEntity(ResponseResult.okResult(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity register(User user) {
        // 对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        // 对数据进行是否重复的判断
        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if(emailExist(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        // 对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        // 存入数据库
        save(user);
        return new ResponseEntity(ResponseResult.okResult(), HttpStatus.OK);
    }

    private boolean emailExist(String email) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        return count(wrapper) > 0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        return count(wrapper) > 0;
    }
}
