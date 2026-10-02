package com.instagram.dao;

import com.instagram.model.Like;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LikeDAOImpl implements LikeDAO {

    private static final Logger logger =
            Logger.getLogger(LikeDAOImpl.class.getName());

    @Override
    public boolean addLike(Like like) {

        String sql = "INSERT INTO likes (user_id, post_id) VALUES (?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, like.getUserId());
            statement.setInt(2, like.getPostId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to add like", e);
            return false;
        }
    }

    @Override
    public boolean removeLike(int userId, int postId) {

        String sql = "DELETE FROM likes WHERE user_id = ? AND post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, postId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to remove like", e);
            return false;
        }
    }

    @Override
    public boolean isLiked(int userId, int postId) {

        String sql = "SELECT COUNT(*) FROM likes WHERE user_id = ? AND post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to check like", e);
        }

        return false;
    }

    @Override
    public int getLikeCount(int postId) {

        String sql = "SELECT COUNT(*) FROM likes WHERE post_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get like count", e);
        }

        return 0;
    }

    @Override
    public List<Like> getLikesByPostId(int postId) {

        String sql = "SELECT * FROM likes WHERE post_id = ?";

        List<Like> likes = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Like like = new Like();

                like.setLikeId(resultSet.getInt("like_id"));
                like.setUserId(resultSet.getInt("user_id"));
                like.setPostId(resultSet.getInt("post_id"));

                likes.add(like);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get likes by post ID", e);
        }

        return likes;
    }

    @Override
    public List<Like> getLikesByUserId(int userId) {

        String sql = "SELECT * FROM likes WHERE user_id = ?";

        List<Like> likes = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Like like = new Like();

                like.setLikeId(resultSet.getInt("like_id"));
                like.setUserId(resultSet.getInt("user_id"));
                like.setPostId(resultSet.getInt("post_id"));

                likes.add(like);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get likes by user ID", e);
        }

        return likes;
    }
}