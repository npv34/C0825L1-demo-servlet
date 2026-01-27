package com.codegym.myapp.entities;

public class Role {
    private int id;
    private String name;
    private int totalUser;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalUser() {
        return totalUser;
    }
    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }
}
