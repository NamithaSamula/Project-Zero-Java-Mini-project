package com.instagram.dao;

import com.instagram.model.Users;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAOImpl implements AdminDAO {

    private static final Logger logger =
            Logger.getLogger(AdminDAOImpl.class.getName());

    @Override
    public List<Users> getAllUsers() {

        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Users users = new Users();

                users.setUserId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(resultSet.getString("email"));
                users.setPasswordHash(resultSet.getString("password_hash"));
                users.setRole(resultSet.getString("role"));
                users.setStatus(resultSet.getString("status"));

                usersList.add(users);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get all users", e);
        }

        return usersList;
    }

    @Override
    public List<Users> searchUsers(String username) {

        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE username LIKE ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + username + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Users users = new Users();

                    users.setUserId(resultSet.getInt("user_id"));
                    users.setUsername(resultSet.getString("username"));
                    users.setEmail(resultSet.getString("email"));
                    users.setPasswordHash(resultSet.getString("password_hash"));
                    users.setRole(resultSet.getString("role"));
                    users.setStatus(resultSet.getString("status"));

                    usersList.add(users);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to search users", e);
        }

        return usersList;
    }

    @Override
    public List<Users> getUsersByStatus(String status) {

        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users WHERE status = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql)) {

            preparedStatement.setString(1, status);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {

                    Users users = new Users();

                    users.setUserId(resultSet.getInt("user_id"));
                    users.setUsername(resultSet.getString("username"));
                    users.setEmail(resultSet.getString("email"));
                    users.setPasswordHash(resultSet.getString("password_hash"));
                    users.setRole(resultSet.getString("role"));
                    users.setStatus(resultSet.getString("status"));

                    usersList.add(users);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get users by status", e);
        }

        return usersList;
    }

    @Override
    public int getTotalUsers() {

        String sql = "SELECT COUNT(*) FROM users";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get total users", e);
        }

        return 0;
    }

    @Override
    public int getTotalPosts() {

        String sql = "SELECT COUNT(*) FROM posts";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get total posts", e);
        }

        return 0;
    }

    @Override
    public int getTotalComments() {

        String sql = "SELECT COUNT(*) FROM comments";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get total comments", e);
        }

        return 0;
    }

    @Override
    public int getTotalLikes() {

        String sql = "SELECT COUNT(*) FROM likes";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to get total likes", e);
        }

        return 0;
    }
}