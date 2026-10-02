package com.instagram.dao;

import com.instagram.model.Comment;
import com.instagram.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDAOImpl implements CommentDAO {

    private static final Logger logger =
            Logger.getLogger(CommentDAOImpl.class.getName());

    @Override
    public boolean addComment(Comment comment) {

        String sql = "INSERT INTO comments (user_id, post_id, parent_comment_id, comment_text) VALUES (?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getPostId());

            if (comment.getParentCommentId() == null) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, comment.getParentCommentId());
            }

            statement.setString(4, comment.getCommentText());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to add comment", e);
        }

        return false;
    }

    @Override
    public Comment getCommentById(int commentId) {

        String sql = "SELECT * FROM comments WHERE comment_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, commentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Comment comment = new Comment();

                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setPostId(resultSet.getInt("post_id"));

                int parentCommentId =
                        resultSet.getInt("parent_comment_id");

                if (resultSet.wasNull()) {
                    comment.setParentCommentId(null);
                } else {
                    comment.setParentCommentId(parentCommentId);
                }

                comment.setCommentText(
                        resultSet.getString("comment_text"));

                return comment;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to get comment", e);
        }

        return null;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {

        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Comment comment = new Comment();

                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setPostId(resultSet.getInt("post_id"));

                int parentCommentId =
                        resultSet.getInt("parent_comment_id");

                if (resultSet.wasNull()) {
                    comment.setParentCommentId(null);
                } else {
                    comment.setParentCommentId(parentCommentId);
                }

                comment.setCommentText(
                        resultSet.getString("comment_text"));

                comments.add(comment);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,
                    "Unable to get comments for post", e);
        }

        return comments;
    }

    @Override
    public List<Comment> getAllComments() {

        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Comment comment = new Comment();

                comment.setCommentId(resultSet.getInt("comment_id"));
                comment.setUserId(resultSet.getInt("user_id"));
                comment.setPostId(resultSet.getInt("post_id"));

                int parentCommentId =
                        resultSet.getInt("parent_comment_id");

                if (resultSet.wasNull()) {
                    comment.setParentCommentId(null);
                } else {
                    comment.setParentCommentId(parentCommentId);
                }

                comment.setCommentText(
                        resultSet.getString("comment_text"));

                comments.add(comment);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to get all comments", e);
        }

        return comments;
    }

    @Override
    public boolean updateComment(Comment comment) {

        String sql = "UPDATE comments SET " +
                "comment_text = ?, parent_comment_id = ? " +
                "WHERE comment_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, comment.getCommentText());

            if (comment.getParentCommentId() == null) {
                statement.setNull(2, Types.INTEGER);
            } else {
                statement.setInt(2, comment.getParentCommentId());
            }

            statement.setInt(3, comment.getCommentId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to update comment", e);
        }

        return false;
    }

    @Override
    public boolean deleteComment(int commentId) {

        String sql = "DELETE FROM comments WHERE comment_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, commentId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to delete comment", e);
        }

        return false;
    }
}