package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-11-02 22:58:13
 */
public interface UserService extends IService<User> {

    public ResponseResult userInfo();
}
