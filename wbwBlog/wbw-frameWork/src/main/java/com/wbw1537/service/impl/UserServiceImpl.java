package com.wbw1537.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.entity.User;
import com.wbw1537.mapper.UserMapper;
import com.wbw1537.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-11-02 22:58:13
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
