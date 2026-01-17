package com.codegym.myapp.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthService {
    public static void renderPageRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp")
                .forward(req, resp);
    }

    public static void renderPageLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // kiem tra request co tham so error khong
        String error = req.getParameter("error");
        if ("true".equals(error)) {
            // truyen thong diep loi sang JSP -> setAttribute cua request
            req.setAttribute("errorMessage", "Invalid username or password");
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(req, resp);
    }

    public static void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
