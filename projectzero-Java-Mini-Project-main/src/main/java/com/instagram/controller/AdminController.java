package com.instagram.controller;

import com.instagram.model.Users;
import com.instagram.service.AdminService;

import java.util.List;

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // User Management

    public List<Users> getAllUsers() {
        return adminService.getAllUsers();
    }

    public List<Users> searchUsers(String username) {
        return adminService.searchUsers(username);
    }

    public List<Users> getUsersByStatus(String status) {
        return adminService.getUsersByStatus(status);
    }

   /* public boolean updateUserStatus(int userId, String status) {
        return adminService.updateUserStatus(userId, status);
    }

    public boolean updateUserRole(int userId, String role) {
        return adminService.updateUserRole(userId, role);
    }

    public boolean deleteUser(int userId) {
        return adminService.deleteUser(userId);
    }*/

    // Admin Dashboard

    public int getTotalUsers() {
        return adminService.getTotalUsers();
    }

    public int getTotalPosts() {
        return adminService.getTotalPosts();
    }

    public int getTotalComments() {
        return adminService.getTotalComments();
    }

    public int getTotalLikes() {
        return adminService.getTotalLikes();
    }
}