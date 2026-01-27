package com.codegym.myapp.services;

import com.codegym.myapp.entities.Role;
import com.codegym.myapp.entities.User;
import com.codegym.myapp.models.Database;
import com.codegym.myapp.models.RoleModel;
import com.codegym.myapp.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final UserModel userModel = new UserModel();
    private static final RoleModel roleModel = new RoleModel();

    public UserService() {
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet result = userModel.getAll();
        while (result.next()){
            int id = result.getInt("id");
            String username = result.getString("username");
            String password = result.getString("password");
            String email = result.getString("email");
            int roleId = result.getInt("role_id");

            // Lay thong tin role tu db dua vao roleId
            ResultSet resultSet = roleModel.getById(roleId);
            Role role = null;
            if (resultSet.next()) {
                String roleName = resultSet.getString("name");
                role = new Role(roleId, roleName);
            }

            User user = new User(id, username, password, email);
            // Gan role cho user
            user.setRole(role);
            users.add(user);
        }
        return users;
    }

    public static void renderPageListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // truyen danh sach users sang JSP
        List<User> users = UserService.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/users/list.jsp")
                .forward(req, resp);
    }

    public static void renderFormCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<Role> roles = getRoleList();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/views/users/create.jsp")
                .forward(req, resp);
    }

    private static List<Role> getRoleList() throws SQLException {
        // get all roles
        List<Role> roles = new ArrayList<>();
        ResultSet result = roleModel.getAll();
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            Role role = new Role(id, name);
            roles.add(role);
        }
        // pass roles to jsp
        return roles;
    }

    public static void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // lay du lieu tu form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        // luu vao db
        userModel.create(username, password, email, roleId);
        // chuyen huong ve trang danh sach user
        response.sendRedirect("/users");
    }

    public static void deleteUserById(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String id = request.getParameter("id");
        userModel.deleteById(Integer.parseInt(id));
        // chuyen huong ve trang danh sach user
        response.sendRedirect("/users");
    }

    public static void renderFormEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String id = req.getParameter("id");
        List<Role> roles = getRoleList();
        req.setAttribute("roles", roles);
        // tim user trong db theo id
        ResultSet resultSet = userModel.getById(Integer.parseInt(id));
        if (resultSet != null) {
            User userEdit = null;
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                int currentRoleId = resultSet.getInt("role_id");
                // Lay thong tin role tu db dua vao roleId
                ResultSet roleResultSet = roleModel.getById(currentRoleId);
                Role role = null;
                if (roleResultSet.next()) {
                    String roleName = roleResultSet.getString("name");
                    role = new Role(currentRoleId, roleName);
                }

                userEdit = new User(userId, username, password, email);
                // Gan role cho user
                userEdit.setRole(role);
            }
            req.setAttribute("user", userEdit);
            req.getRequestDispatcher("/WEB-INF/views/users/edit.jsp")
                    .forward(req, resp);
        } else {
            // khong tim thay user, hien thi trang 404
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp")
                    .forward(req, resp);
        }
    }

    public static void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        ResultSet resultSet = userModel.getById(Integer.parseInt(id));
        if (resultSet != null){
            userModel.updateById(Integer.parseInt(id), username, email, roleId);
            res.sendRedirect("/users");
        } else {
            // khong tim thay user, hien thi trang 404
            req.getRequestDispatcher("/WEB-INF/views/error/404.jsp")
                    .forward(req, res);
        }
    }

    public static void searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String keyword = request.getParameter("keyword");
        List<User> result = new ArrayList<>();
        ResultSet rs = userModel.search(keyword);
        while (rs.next()){
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            User user = new User(id, username, password, email);
            result.add(user);
        }
        request.setAttribute("users", result);
        request.getRequestDispatcher("/WEB-INF/views/users/list.jsp")
                .forward(request, response);
    }

    public static int getTotalUserCount() throws SQLException {
        ResultSet resultSet = userModel.getTotalUsers();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }
}
