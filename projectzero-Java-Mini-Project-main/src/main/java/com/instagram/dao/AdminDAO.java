package com.instagram.dao;

import com.instagram.model.Users;

import java.util.List;

public interface AdminDAO {

    // User Management
    List<Users> getAllUsers();

    List<Users> searchUsers(String username);

    List<Users> getUsersByStatus(String status);

    // Application Monitoring / Dashboard
    int getTotalUsers();

    int getTotalPosts();

    int getTotalComments();

    int getTotalLikes();
}