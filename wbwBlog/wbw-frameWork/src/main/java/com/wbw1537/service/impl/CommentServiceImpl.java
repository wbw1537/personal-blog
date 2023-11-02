package com.wbw1537.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbw1537.domain.ResponseResult;
import com.wbw1537.domain.entity.Comment;
import com.wbw1537.domain.entity.User;
import com.wbw1537.domain.vo.CommentVo;
import com.wbw1537.domain.vo.PageVo;
import com.wbw1537.mapper.CommentMapper;
import com.wbw1537.service.CommentService;
import com.wbw1537.service.UserService;
import com.wbw1537.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-11-02 21:54:28
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {


    //TODO:直接从数据库循环读取数据会大量消耗数据库资源，需要优化

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询对应文章的根评论


        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(Comment::getArticleId, articleId);
        // 根评论 rootId为-1
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        // 分页查询
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page = page(page, queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(page.getRecords());
        // 查询所有根根评论对应的子评论的集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            // 查询对应的子评论
            List<CommentVo> children =  getChildren(commentVo.getId());
            // 赋值
            commentVo.setChildren(children);

        }
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    /**
     * 根据根评论的id查询所对应的子评论的集合
     * @param id 根评论的id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> commentList = list(queryWrapper);
        List<CommentVo> commentVoList = toCommentVoList(commentList);
        return commentVoList;
    }


    //将commentList转换为commentVoList
    private List<CommentVo> toCommentVoList(List<Comment> commentList){
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        for(CommentVo commentVo : commentVoList){
            //通过查询creatBy查询用户名称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setNickName(nickName);
            //如果toCommentUserId不为-1，说明是回复评论，需要查询回复的用户名称并赋值
            //通过toCommentUserId查询用户的名称并赋值
            if(commentVo.getToCommentUserId() != -1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }

        return commentVoList;
    }
}
