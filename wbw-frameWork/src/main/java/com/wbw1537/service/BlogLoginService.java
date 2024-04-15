package com.wbw1537.service;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.User;
import org.springframework.stereotype.Service;


public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
