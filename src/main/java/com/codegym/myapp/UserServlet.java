package com.codegym.myapp;

import com.codegym.myapp.entities.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {
    private static List<User> users = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        users.add(new User(1, "john_doe", "password123", "john@gmail.com"));
        users.add(new User(2, "jane_smith", "securepass", "smith@gmail.com"));
        users.add(new User(3, "jane_smith", "securepass", "smith@gmail.com"));
        users.add(new User(4, "jane_smith", "securepass", "smith@gmail.com"));
        // get users trong db
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        switch (pathInfo) {
            case "/":
                renderPageListUser(req, resp);
                break;
            case "/add":

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private static void renderPageListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // truyen danh sach users sang JSP
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users/list.jsp")
                .forward(req, resp);
    }
}
