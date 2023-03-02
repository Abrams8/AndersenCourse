package com.shop.productservice.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class MyConnection {
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    @Value("${database.url}")
    private void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }
    @Value("${database.username}")
    private void setDbUser(String dbUser) {
        DB_USER = dbUser;
    }
    @Value("${database.password}")
    private void setDbPassword(String dbPassword) {
        DB_PASSWORD = dbPassword;
    }

    public static Connection getRealConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
