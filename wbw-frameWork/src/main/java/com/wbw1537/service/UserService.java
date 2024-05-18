package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.dto.UserInfoDto;
import com.wbw1537.domain.entity.User;
import org.springframework.http.ResponseEntity;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-11-02 22:58:13
 */
public interface UserService extends IService<User> {

    public ResponseEntity userInfo();

    ResponseEntity updateUserInfo(UserInfoDto user);

    ResponseEntity register(User user);
}
