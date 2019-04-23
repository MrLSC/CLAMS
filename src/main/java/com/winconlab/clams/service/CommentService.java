package com.winconlab.clams.service;

import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.VideoComment;

public interface CommentService {
    GlobalResult findAllComments();

    GlobalResult findCommentsByUserId(int userId);

    GlobalResult addComment(VideoComment videoComment);

    GlobalResult delComment(int commentId);
}
