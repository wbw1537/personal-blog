package com.wbw1537.controller;

import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Comment;
import com.wbw1537.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
