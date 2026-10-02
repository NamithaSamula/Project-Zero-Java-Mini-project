package com.instagram.service;

import com.instagram.dao.CommentDAO;
import com.instagram.dao.CommentDAOImpl;
import com.instagram.model.Comment;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    public CommentServiceImpl() {
        commentDAO = new CommentDAOImpl();
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentDAO.getCommentById(commentId);
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        return commentDAO.getCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentDAO.updateComment(comment);
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentDAO.deleteComment(commentId);
    }
}