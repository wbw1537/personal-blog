package com.wbw1537.controller;

import com.wbw1537.constants.SystemConstants;
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

    // 文章评论
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }

    // 友链评论
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }
}
