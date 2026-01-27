package com.codegym.myapp.models;

import java.sql.*;

public class RoleModel extends BaseModel{

    public ResultSet getAll() throws SQLException {
        String sql = "CALL getAllRoleWithTotalUser()";
        Statement statement = conn.prepareStatement(sql);
        return statement.executeQuery(sql);
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM roles WHERE id = " + id;
        Statement statement = conn.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public ResultSet getById(int id) throws SQLException {
        String sql = "SELECT * FROM roles WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        return statement.executeQuery();
    }

    public void updateById(int id, String name) throws SQLException {
        String sql = "UPDATE roles SET name = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    public void create(String name) throws SQLException {
        System.out.println("name in model: " + name);
        String sql = "INSERT INTO roles (name) VALUES (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.execute();
    }
}
