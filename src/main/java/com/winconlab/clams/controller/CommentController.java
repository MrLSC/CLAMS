package com.winconlab.clams.controller;

import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.VideoComment;
import com.winconlab.clams.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/getComments")
    public GlobalResult getComments() {
        return commentService.findAllComments();
    }

    @RequestMapping("/getCommentsByUserId")
    private GlobalResult getCommentsByUserId(int userId) {
        return commentService.findCommentsByUserId(userId);
    }

    @RequestMapping("/addComment")
    private GlobalResult addComment(@Valid VideoComment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.build(-1, bindingResult.getFieldError().getDefaultMessage());
        }

        return commentService.addComment(comment);
    }

    @RequestMapping("/delComment")
    private GlobalResult delComment(int commentId) {
        return commentService.delComment(commentId);
    }

}
