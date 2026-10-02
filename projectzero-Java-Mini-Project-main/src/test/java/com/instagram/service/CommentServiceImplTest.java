package com.instagram.service;

import com.instagram.model.Comment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {

    @Test
    void addComment() {

        CommentService commentService = new CommentServiceImpl();

        Comment comment = new Comment();

        comment.setUserId(14);
        comment.setPostId(4);
        comment.setParentCommentId(null);
        comment.setCommentText("Best post!");

        boolean result = commentService.addComment(comment);

        assertTrue(result);
    }

    @Test
    void getCommentById() {

        CommentService commentService = new CommentServiceImpl();

        Comment comment = commentService.getCommentById(1);

        assertNotNull(comment);
        assertEquals(17, comment.getUserId());
        assertEquals(1, comment.getPostId());
        assertEquals("Nice post!", comment.getCommentText());
    }

    @Test
    void getCommentsByPostId() {
        CommentService commentService = new CommentServiceImpl();

        List<Comment> comments =
                commentService.getCommentsByPostId(1);

        assertNotNull(comments);
        assertFalse(comments.isEmpty());

        assertEquals(1, comments.get(0).getPostId());
    }

    @Test
    void getAllComments() {
        CommentService commentService = new CommentServiceImpl();

        List<Comment> comments =
                commentService.getAllComments();

        assertNotNull(comments);
        assertFalse(comments.isEmpty());
    }

    @Test
    void updateComment() {
        CommentService commentService = new CommentServiceImpl();

        Comment comment = new Comment();

        comment.setCommentId(1);
        comment.setCommentText("Really nice post!");
        comment.setParentCommentId(null);

        boolean result = commentService.updateComment(comment);

        assertTrue(result);

        Comment updatedComment =
                commentService.getCommentById(1);

        assertNotNull(updatedComment);
        assertEquals("Really nice post!",
                updatedComment.getCommentText());
    }

    @Test
    void deleteComment() {

        CommentService commentService = new CommentServiceImpl();

        boolean result = commentService.deleteComment(5);

        assertTrue(result);

        Comment comment =
                commentService.getCommentById(5);

        assertNull(comment);
    }

    @Test
    void addReply() {

        CommentService commentService = new CommentServiceImpl();

        Comment reply = new Comment();

        reply.setUserId(14);
        reply.setPostId(1);
        reply.setParentCommentId(1);
        reply.setCommentText("Thank you!");

        boolean result = commentService.addComment(reply);

        assertTrue(result);
    }
}