package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.BackGroundImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class BackGroundImgController {

    @Autowired
    private BackGroundImgService backGroundImgService;
    @GetMapping("/backGroundImg/{id}")// 根据用户id获取背景图
    public ResponseResult getBackGroundImgList(@PathVariable Long id){
        ResponseResult result = backGroundImgService.getBackGroundImgList(id);
        return result;
    }

}
