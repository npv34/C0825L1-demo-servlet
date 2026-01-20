package com.codegym.myapp.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456@Abc";

    public Database() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Could not connect to the database");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
