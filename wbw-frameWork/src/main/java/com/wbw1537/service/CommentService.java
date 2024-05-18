package com.wbw1537.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Comment;
import org.springframework.http.ResponseEntity;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-11-02 21:54:28
 */
public interface CommentService extends IService<Comment> {

    ResponseEntity commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseEntity addComment(Comment comment);
}
