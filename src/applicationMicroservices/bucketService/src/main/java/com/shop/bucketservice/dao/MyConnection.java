package com.shop.bucketservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/shop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1111";

    public static Connection getRealConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
