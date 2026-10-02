package com.instagram.service;

import com.instagram.dao.UserDAO;
import com.instagram.dao.UserDAOImpl;
import com.instagram.model.Users;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean registerUser(Users users) {
        if (users == null) {
            return false;
        }

        if (users.getUsername() == null || users.getUsername().trim().isEmpty()) {
            return false;
        }

        if (users.getEmail() == null || users.getEmail().trim().isEmpty()) {
            return false;
        }

        if (users.getPasswordHash() == null || users.getPasswordHash().trim().isEmpty()) {
            return false;
        }
        return userDAO.addUser(users);
    }

    @Override
    public Users login(String username, String password) {
        return userDAO.login(username, password);
    }

    @Override
    public Users getUserById(int userId) {

        return userDAO.getUserById(userId);
    }

    @Override
    public Users getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean updateUser(Users users) {
        // TODO: Add validation
        return userDAO.updateUser(users);
    }

    //    @Override
    public boolean deleteUser(int userId) {

        return userDAO.deleteUser(userId);
    }

}