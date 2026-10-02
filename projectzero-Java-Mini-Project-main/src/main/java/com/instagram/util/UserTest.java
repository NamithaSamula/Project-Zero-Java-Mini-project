package com.instagram.util;

import com.instagram.model.Users;
import com.instagram.service.UserService;
import com.instagram.service.UserServiceImpl;

import java.util.Scanner;

public class UserTest {

    public static void main(String[] args)  {

        /*UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUsername("Nam123");
        users.setEmail("namitha@gmail.com");
        users.setPasswordHash("password1234");
        //user.setStatus("ACTIVE");
        //user.setRole("USER");

        boolean result = userService.registerUser(users);

        if (result) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }*/






        Scanner sc = new Scanner(System.in);

        UserService userService = new UserServiceImpl();

        System.out.println("===== Instagram User Registration =====");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Users users = new Users();

        users.setUsername(username);
        users.setEmail(email);
        users.setPasswordHash(password);

        boolean result = userService.registerUser(users);

        if (result) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }

        sc.close();
    }




}