package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;
    @GetMapping("/list")
    public ResponseResult getTagList(){
        return ResponseResult.okResult(tagService.list());
    }
}
