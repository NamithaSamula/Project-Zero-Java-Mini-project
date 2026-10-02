package com.instagram.dao;

import com.instagram.model.Users;

import java.util.List;

public interface UserDAO {

    boolean addUser(Users users);
    Users login(String username, String password);

    Users getUserById(int userId);

    Users getUserByUsername(String username);

    //Users getUserByEmail(String email);

    List<Users> getAllUsers();

    boolean updateUser(Users users);

    boolean deleteUser(int userId);
}