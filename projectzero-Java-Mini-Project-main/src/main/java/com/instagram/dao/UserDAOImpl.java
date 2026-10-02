package com.instagram.dao;

import com.instagram.model.Users;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(Users users) {

        String sql = "INSERT INTO users " +
                "(username, email, password_hash, role, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, users.getUsername());
            statement.setString(2, users.getEmail());
            statement.setString(3, users.getPasswordHash());
            statement.setString(4,
                    users.getRole() == null ? "USER" : users.getRole());
            statement.setString(5,
                    users.getStatus() == null ? "ACTIVE" : users.getStatus());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Unable to add user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Users login(String username, String password) {

        String sql = "SELECT * FROM users " +
                "WHERE username = ? AND password_hash = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Users users = new Users();

                    users.setUserId(resultSet.getInt("user_id"));
                    users.setUsername(resultSet.getString("username"));
                    users.setEmail(resultSet.getString("email"));
                    users.setPasswordHash(
                            resultSet.getString("password_hash"));
                    users.setRole(resultSet.getString("role"));
                    users.setStatus(resultSet.getString("status"));

                    return users;
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to login: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Users getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Users user = new Users();

                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to get user: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Users getUserByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Users user = new Users();

                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordHash(
                            resultSet.getString("password_hash"));
                    user.setRole(resultSet.getString("role"));
                    user.setStatus(resultSet.getString("status"));

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Unable to get user by username: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Users> getAllUsers() {

        String sql = "SELECT * FROM users";

        List<Users> usersList = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Users users = new Users();

                users.setUserId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(resultSet.getString("email"));
                users.setPasswordHash(
                        resultSet.getString("password_hash"));
                users.setRole(resultSet.getString("role"));
                users.setStatus(resultSet.getString("status"));

                usersList.add(users);
            }

            return usersList;

        } catch (SQLException e) {
            System.out.println("Unable to get users: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public boolean updateUser(Users users) {

        String sql = "UPDATE users " +
                "SET username = ?, email = ?, password_hash = ? " +
                "WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, users.getUsername());
            statement.setString(2, users.getEmail());
            statement.setString(3, users.getPasswordHash());
            statement.setInt(4, users.getUserId());

            int updatedRows = statement.executeUpdate();

            return updatedRows > 0;

        } catch (SQLException e) {
            System.out.println("Unable to update user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {

        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            int deletedRows = statement.executeUpdate();

            return deletedRows > 0;

        } catch (SQLException e) {
            System.out.println("Unable to delete user: " + e.getMessage());
            return false;
        }
    }
}