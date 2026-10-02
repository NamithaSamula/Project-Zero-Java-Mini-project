package com.instagram.dao;

import com.instagram.model.Follow;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FollowDAOImpl implements FollowDAO {

    private static final Logger logger =
            Logger.getLogger(FollowDAOImpl.class.getName());

    @Override
    public boolean addFollow(Follow follow) {

        String sql = "INSERT INTO follows (follower_id, following_id) VALUES (?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, follow.getFollowerId());
            statement.setInt(2, follow.getFollowingId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to add follow", e);
            return false;
        }
    }

    @Override
    public boolean removeFollow(int followerId, int followingId) {

        String sql = "DELETE FROM follows WHERE follower_id = ? AND following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, followerId);
            statement.setInt(2, followingId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to remove follow", e);
            return false;
        }
    }

    @Override
    public boolean isFollowing(int followerId, int followingId) {

        String sql = "SELECT COUNT(*) FROM follows " +
                "WHERE follower_id = ? AND following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, followerId);
            statement.setInt(2, followingId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to check following status", e);
        }

        return false;
    }

    @Override
    public List<Follow> getFollowers(int followingId) {

        String sql = "SELECT * FROM follows WHERE following_id = ?";

        List<Follow> follows = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, followingId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Follow follow = new Follow();

                follow.setFollowId(resultSet.getInt("follow_id"));
                follow.setFollowerId(resultSet.getInt("follower_id"));
                follow.setFollowingId(resultSet.getInt("following_id"));

                follows.add(follow);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get followers", e);
        }

        return follows;
    }

    @Override
    public List<Follow> getFollowing(int followerId) {

        String sql = "SELECT * FROM follows WHERE follower_id = ?";

        List<Follow> follows = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, followerId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Follow follow = new Follow();

                follow.setFollowId(resultSet.getInt("follow_id"));
                follow.setFollowerId(resultSet.getInt("follower_id"));
                follow.setFollowingId(resultSet.getInt("following_id"));

                follows.add(follow);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get following", e);
        }

        return follows;
    }

    @Override
    public int getFollowerCount(int userId) {
        String sql = "SELECT COUNT(*) FROM follows WHERE following_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get follower count", e);
        }

        return 0;
    }

    @Override
    public int getFollowingCount(int userId) {
        String sql = "SELECT COUNT(*) FROM follows WHERE follower_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Unable to get following count", e);
        }

        return 0;
    }
}