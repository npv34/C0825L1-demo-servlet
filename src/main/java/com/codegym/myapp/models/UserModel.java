package com.codegym.myapp.models;

import com.codegym.myapp.entities.User;

import java.sql.*;

public class UserModel extends BaseModel {

    public ResultSet getAll() throws SQLException {
        String sql = "CALL getAllUserWithRole()";
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

    public void updateById(int id, String username, String email, int roleId) throws SQLException {
        String sql = "UPDATE users SET username = ? , email = ? , role_id = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setInt(3, roleId);
        statement.setInt(4, id);
        statement.execute();
    }
    public void create(String username, String password, String email, int roleId) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setInt(4, roleId);
        statement.execute();
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
