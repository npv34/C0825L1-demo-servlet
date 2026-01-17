package com.codegym.myapp.controllers;

import com.codegym.myapp.services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "userServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        UserService userService = new UserService();
        userService.initData();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        switch (pathInfo) {
            case "/":
                UserService.renderPageListUser(req, resp);
                break;
            case "/create":
                UserService.renderFormCreateUser(req, resp);
                break;
            case "/delete":
                UserService.deleteUserById(req, resp);
                break;
            case "/edit":
                UserService.renderFormEditUser(req, resp);
                break;
            case "/search":
                UserService.searchUser(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        switch (pathInfo) {
            case "/create":
                UserService.createUser(req, resp);
                break;
            case "/edit":
                UserService.updateUser(req, resp);
                break;
        }
    }

}
