package com.codegym.myapp;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authServlet", urlPatterns = {"/auth/*"})
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        switch (pathInfo) {
            case "/login":
                renderPageLogin(req, resp);
                break;
            case "/register":
                renderPageRegister(req, resp);
                break;
        }

    }

    private static void renderPageRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(req, resp);
    }

    private static void renderPageLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // kiem tra request co tham so error khong
        String error = req.getParameter("error");
        if ("true".equals(error)) {
            // truyen thong diep loi sang JSP -> setAttribute cua request
            req.setAttribute("errorMessage", "Invalid username or password");
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }

        switch (pathInfo) {
            case "/login":
                handleLogin(req, resp);
                break;
        }
    }

    private static void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // xu ly du lieu tu form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // xu ly logic dang nhap
        if ("admin".equals(username) && "1234".equals(password)) {
            // chuyen huong ve trang chu
            resp.sendRedirect("/home");
        } else {
            resp.sendRedirect("/auth/login?error=true");
        }
    }
}

// https://codegym.vn/api/learn-java-web/courses/1/lessons/1106?unit=1303
// https -> protocol
// codegym.vn -> host
// /api/learn-java-web/courses/1/lessons/1106 -> path
// 1 or 1106  -> path variable(path parameter)
// ?unit=1303 -> query string

// /auth/login?error=true