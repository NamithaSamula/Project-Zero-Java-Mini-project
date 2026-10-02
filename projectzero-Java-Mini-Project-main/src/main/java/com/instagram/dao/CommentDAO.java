package com.instagram.dao;

import com.instagram.model.Comment;

import java.util.List;

public interface CommentDAO {

    boolean addComment(Comment comment);

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByPostId(int postId);

    List<Comment> getAllComments();

    boolean updateComment(Comment comment);

    boolean deleteComment(int commentId);
}