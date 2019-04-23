package com.winconlab.clams.service.impl;

import com.winconlab.clams.mapper.VideoCommentMapper;
import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.VideoComment;
import com.winconlab.clams.pojo.VideoCommentExample;
import com.winconlab.clams.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private VideoCommentMapper commentMapper;

    @Override
    public GlobalResult findAllComments() {
        VideoCommentExample example = new VideoCommentExample();
        VideoCommentExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(0);
        List<VideoComment> comments = commentMapper.selectByExample(example);
        return GlobalResult.ok(comments);
    }

    @Override
    public GlobalResult findCommentsByUserId(int userId) {
        VideoCommentExample example = new VideoCommentExample();
        VideoCommentExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId).andStateEqualTo(0);
        List<VideoComment> comments = commentMapper.selectByExample(example);
        return GlobalResult.ok(comments);
    }

    @Override
    public GlobalResult addComment(VideoComment videoComment) {
        Date date = new Date();
        videoComment.setCommentTime(date);
        videoComment.setState(0);
        commentMapper.insert(videoComment);
        return GlobalResult.ok();
    }

    @Override
    public GlobalResult delComment(int commentId) {
        VideoComment videoComment = commentMapper.selectByPrimaryKey(commentId);
        videoComment.setState(1);
        commentMapper.updateByPrimaryKey(videoComment);
        return GlobalResult.ok();
    }
}
