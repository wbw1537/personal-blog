package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.UserInfoVo;
import com.wbw1537.mapper.UserMapper;
import com.wbw1537.service.UserService;
import com.wbw1537.utils.BeanCopyUtils;
import com.wbw1537.utils.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-11-02 22:58:13
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        // 获取当前用户id
        Long userId = SecurityUtils.getUserId();
        // 根据id查询用户信息
        User user = getById(userId);
        // 封装成userInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }
}
