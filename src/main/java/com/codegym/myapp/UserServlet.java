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
import java.util.regex.Pattern;

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
            case "/create":
                renderFormCreateUser(req, resp);
                break;
            case "/delete":
                deleteUserById(req, resp);
                break;
            case "/edit":
                renderFormEditUser(req, resp);
                break;
            case "/search":
                     searchUser(req, resp);
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
                createUser(req, resp);
                break;
            case "/edit":
                updateUser(req, resp);
                break;
        }
    }

    private static void renderPageListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // truyen danh sach users sang JSP
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users/list.jsp")
                .forward(req, resp);
    }

    private static void renderFormCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/create.jsp")
                .forward(req, resp);
    }

    private static void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // lay du lieu tu form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // tao user moi
        User newUser = new User();
        newUser.setId(users.size() + 1);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        // luu user moi vao danh sach
        users.add(newUser);

        // chuyen huong ve trang danh sach user
        response.sendRedirect("/users");
    }

    private static void deleteUserById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        //  tim user trong danh sach theo id va xoa
        User userDelete = null;
        for (User user : users) {
            if (String.valueOf(user.getId()).equals(id)) {
                userDelete = user;
                break;
            }
        }

        // xoa user neu tim thay
        if (userDelete != null) {
            users.remove(userDelete);
        }

        // chuyen huong ve trang danh sach user
        response.sendRedirect("/users");
    }

    private static void renderFormEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        // tim user trong danh sach theo id
        User userEdit = null;
        for (User user : users) {
            if (String.valueOf(user.getId()).equals(id)) {
                userEdit = user;
                break;
            }
        }

        if (userEdit != null) {
            req.setAttribute("user", userEdit);
            req.getRequestDispatcher("/WEB-INF/views/users/edit.jsp")
                    .forward(req, resp);
        } else {
            // khong tim thay user, hien thi trang 404
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp")
                    .forward(req, resp);
        }
    }

    private static void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");


        User userEdit = null;
        for (User user : users) {
            if (String.valueOf(user.getId()).equals(id)) {
                userEdit = user;
                break;
            }
        }

        if (userEdit != null){
            userEdit.setUsername(username);
            userEdit.setEmail(email);
            res.sendRedirect("/users");
        } else {
            // khong tim thay user, hien thi trang 404
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp")
                    .forward(req, res);
        }
    }

    public static void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<User> result = new ArrayList<>();
        for (User user: users) {
            if (user.getUsername().equals(keyword)) {
                result.add(user);
            }
        }
        System.out.println(result.size());
        request.setAttribute("users", result);
        request.getRequestDispatcher("/WEB-INF/views/users/list.jsp")
                .forward(request, response);
    }
}
