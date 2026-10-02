package com.instagram.util;

import java.sql.Connection;

public class JDBCConnectionTest {

    public static void main(String[] args) {

        try (Connection connection = JDBCUtil.getConnection()) {

            System.out.println("Database connected successfully!");
            System.out.println("Database: " + connection.getCatalog());

        } catch (Exception e) {

            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}