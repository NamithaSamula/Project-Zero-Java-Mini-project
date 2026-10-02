package com.instagram.controller;

import com.instagram.model.Comment;
import com.instagram.service.CommentService;

import java.util.List;

public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public boolean addComment(Comment comment) {
        return commentService.addComment(comment);
    }

    public Comment getCommentById(int commentId) {
        return commentService.getCommentById(commentId);
    }

    public List<Comment> getCommentsByPostId(int postId) {
        return commentService.getCommentsByPostId(postId);
    }

   /* public List<Comment> getCommentsByUserId(int userId) {
        return commentService.getCommentsByUserId(userId);
    }*/

    /*public List<Comment> getReplies(int parentCommentId) {
        return commentService.getReplies(parentCommentId);
    }*/

    public boolean updateComment(Comment comment) {
        return commentService.updateComment(comment);
    }

    public boolean deleteComment(int commentId) {
        return commentService.deleteComment(commentId);
    }
}