package com.codegym.myapp.models;

import com.codegym.myapp.entities.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserModel {
    private Connection conn;
    public UserModel(Connection conn) {
        this.conn = conn;
    }

    public ResultSet getAll() throws SQLException {
        String sql = "SELECT * FROM users";
        Statement statement = conn.prepareStatement(sql);
        return statement.executeQuery(sql);
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = " + id;
        Statement statement = conn.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public ResultSet getById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = " + id;
        Statement statement = conn.prepareStatement(sql);
        return statement.executeQuery(sql);
    }

    public void updateById(int id, String username, String email) throws SQLException {
        String sql = "UPDATE users SET username = '" + username + "', email = '" + email + "' WHERE id = " + id;
        Statement statement = conn.prepareStatement(sql);
        statement.executeUpdate(sql);
    }
    public void create(String username, String password, String email) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
        Statement statement = conn.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public ResultSet search(String keyword) throws SQLException {
        String sql = "SELECT * FROM users WHERE username LIKE '%" + keyword + "%' OR email LIKE '%" + keyword + "%'";
        Statement statement = conn.prepareStatement(sql);
        return statement.executeQuery(sql);
    }

    public ResultSet getTotalUsers() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM users";
        Statement statement = conn.prepareStatement(sql);
        return statement.executeQuery(sql);
    }
}
