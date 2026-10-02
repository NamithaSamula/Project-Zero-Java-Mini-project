package com.instagram.service;

import com.instagram.model.Users;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceImplTest {

    @Test
    void getAllUsers() {
        AdminService adminService = new AdminServiceImpl();

        List<Users> users = adminService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        System.out.println(users);
    }

    @Test
    void searchUsers() {
        AdminService adminService = new AdminServiceImpl();

        List<Users> users = adminService.searchUsers("Nikitha");

        assertNotNull(users);
        assertFalse(users.isEmpty());

        assertTrue(users.get(0).getUsername().contains("Nikitha"));
        System.out.println(users);
    }

    @Test
    void getUsersByStatus() {
        AdminService adminService = new AdminServiceImpl();

        List<Users> users =
                adminService.getUsersByStatus("ACTIVE");

        assertNotNull(users);
        assertFalse(users.isEmpty());

        assertEquals("ACTIVE", users.get(0).getStatus());

    }

    @Test
    void getInactiveUsers() {

        AdminService adminService = new AdminServiceImpl();

        List<Users> users =
                adminService.getUsersByStatus("INACTIVE");

        assertNotNull(users);

        for (Users user : users) {
            assertEquals("INACTIVE", user.getStatus());
        }
    }
    @Test
    void getTotalUsers() {
        AdminService adminService = new AdminServiceImpl();

        int count = adminService.getTotalUsers();

        assertTrue(count > 0);
        System.out.println(count);
    }

    @Test
    void getTotalPosts() {
        AdminService adminService = new AdminServiceImpl();

        int count = adminService.getTotalPosts();

        assertTrue(count >= 0);
        System.out.println(count);
    }

    @Test
    void getTotalComments() {
        AdminService adminService = new AdminServiceImpl();

        int count = adminService.getTotalComments();

        assertTrue(count >= 0);
        System.out.println(count);
    }

    @Test
    void getTotalLikes() {
        AdminService adminService = new AdminServiceImpl();

        int count = adminService.getTotalLikes();

        assertTrue(count >= 0);
        System.out.println(count);
    }
}