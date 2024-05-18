package com.wbw1537.controller;

import com.wbw1537.constants.SystemConstants;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Comment;
import com.wbw1537.enums.AppHttpCodeEnum;
import com.wbw1537.exception.SystemException;
import com.wbw1537.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "CommentController", description = "Comment Related APIs")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "Get Comment List")
    @GetMapping("/commentList")
    public ResponseEntity commentList(Long articleId, Integer pageNum, Integer pageSize) {
        if(articleId == null || pageNum == null || pageSize == null){
            throw new IllegalArgumentException("Parameter is required");
        }
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId, pageNum, pageSize);
    }


    @ApiOperation(value = "Get Link Comment List")
    @GetMapping("/linkCommentList")
    public ResponseEntity linkCommentList(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null){
            throw new IllegalArgumentException("Parameter is required");
        }
        return commentService.commentList(SystemConstants.LINK_COMMENT,null, pageNum, pageSize);
    }

    @ApiOperation(value = "Add Comment")
    @PostMapping
    public ResponseEntity addComment(@RequestBody Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new IllegalArgumentException("Comment content can't be null!");
        }
        return commentService.addComment(comment);
    }
}
