package com.codegym.myapp.models;

import java.sql.Connection;

public class BaseModel {
    protected Connection conn;

    public BaseModel() {
        this.conn = Database.getConnection();
    }
}
