package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.User;

public interface AdminLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
