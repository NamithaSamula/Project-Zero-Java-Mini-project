package com.instagram.controller;

import com.instagram.model.Users;
import com.instagram.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean registerUser(Users users) {
        return userService.registerUser(users);
    }

    public Users login(String username, String password) {
        return userService.login(username, password);
    }

    public Users getUserById(int userId) {
        // UserService currently does not have getUserById()
        return userService.getUserById(userId);
    }

    public Users getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean updateUser(Users users) {
        return userService.updateUser(users);
    }

    public boolean deactivateUser(int userId) {
        // UserService currently does not have a deactivateUser() method
        return false;
    }
}