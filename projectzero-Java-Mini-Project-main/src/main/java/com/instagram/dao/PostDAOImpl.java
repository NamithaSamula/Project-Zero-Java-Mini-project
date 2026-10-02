package com.instagram.dao;

import com.instagram.model.Post;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostDAOImpl implements PostDAO {

    private static final Logger logger =
            Logger.getLogger(PostDAOImpl.class.getName());

    @Override
    public boolean addPost(Post post) {

        String sql = "INSERT INTO posts (user_id, caption, image_url) VALUES (?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, post.getUserId());
            statement.setString(2, post.getCaption());
            statement.setString(3, post.getImageUrl());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info("Post added successfully for user ID: "
                        + post.getUserId());
                return true;
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,
                    "Unable to add post for user ID: "
                            + post.getUserId(), e);
        }

        return false;
    }

    @Override
    public Post getPostById(int postId) {

        String sql = "SELECT * FROM posts WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Post post = new Post();

                post.setPostId(resultSet.getInt("post_id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setCaption(resultSet.getString("caption"));
                post.setImageUrl(resultSet.getString("image_url"));

                return post;
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Unable to get post with ID: " + postId, e);
        }

        return null;
    }

    @Override
    public List<Post> getPostsByUserId(int userId) {

        String sql = "SELECT * FROM posts WHERE user_id = ?";

        List<Post> posts = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Post post = new Post();

                post.setPostId(resultSet.getInt("post_id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setCaption(resultSet.getString("caption"));
                post.setImageUrl(resultSet.getString("image_url"));

                posts.add(post);
            }

            return posts;

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Unable to get posts for user ID: " + userId, e);

            return List.of();
        }
    }

    @Override
    public List<Post> getAllPosts() {
        String sql = "SELECT * FROM posts";

        List<Post> posts = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Post post = new Post();

                post.setPostId(resultSet.getInt("post_id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setCaption(resultSet.getString("caption"));
                post.setImageUrl(resultSet.getString("image_url"));

                posts.add(post);
            }

            return posts;

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Unable to get all posts", e);

            return List.of();
        }
    }

    @Override
    public boolean updatePost(Post post) {

        String sql = "UPDATE posts SET caption = ?, image_url = ? WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, post.getCaption());
            statement.setString(2, post.getImageUrl());
            statement.setInt(3, post.getPostId());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info("Post updated successfully with ID: "
                        + post.getPostId());
                return true;
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Unable to update post with ID: "
                            + post.getPostId(), e);
        }

        return false;
    }

    @Override
    public boolean deletePost(int postId) {
        String sql = "DELETE FROM posts WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                logger.info("Post deleted successfully with ID: "
                        + postId);
                return true;
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE,
                    "Unable to delete post with ID: " + postId, e);
        }

        return false;
    }
}