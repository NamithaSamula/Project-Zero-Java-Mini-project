package com.instagram.service;

import com.instagram.model.Users;

import java.util.List;

public interface UserService {

    boolean registerUser(Users users);

    Users login(String username, String password);

    Users getUserById(int userId);

    Users getUserByUsername(String username);

    List<Users> getAllUsers();

    boolean updateUser(Users users);

    boolean deleteUser(int userId);
}