package com.instagram.service;

import com.instagram.dao.AdminDAO;
import com.instagram.dao.AdminDAOImpl;
import com.instagram.model.Users;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO;

    public AdminServiceImpl() {
        adminDAO = new AdminDAOImpl();
    }

    @Override
    public List<Users> getAllUsers() {
        return adminDAO.getAllUsers();
    }

    @Override
    public List<Users> searchUsers(String username) {

        if (username == null || username.trim().isEmpty()) {
            return List.of();
        }

        return adminDAO.searchUsers(username);
    }

    @Override
    public List<Users> getUsersByStatus(String status) {
        return adminDAO.getUsersByStatus(status);
    }

    @Override
    public int getTotalUsers() {
        return adminDAO.getTotalUsers();
    }

    @Override
    public int getTotalPosts() {
        return adminDAO.getTotalPosts();
    }

    @Override
    public int getTotalComments() {
        return adminDAO.getTotalComments();
    }

    @Override
    public int getTotalLikes() {
        return adminDAO.getTotalLikes();
    }
}