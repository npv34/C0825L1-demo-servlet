package com.codegym.myapp.controllers;

import com.codegym.myapp.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "homeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userCount = 0;
        try {
            userCount = UserService.getTotalUserCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("userCount", userCount);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp")
                .forward(req, resp);
    }
}
