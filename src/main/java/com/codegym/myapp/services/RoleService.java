package com.codegym.myapp.services;

import com.codegym.myapp.entities.Role;
import com.codegym.myapp.models.Database;
import com.codegym.myapp.models.RoleModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleService {
    private static final RoleModel roleModel = new RoleModel(Database.getConnection());

    public RoleService() {
    }

    public static List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<>();
        ResultSet result = roleModel.getAll();
        while (result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");

            Role role = new Role(id, name);
            roles.add(role);
        }
        return roles;
    }

    public static void createRole(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException, SQLException {
        // lay du lieu tu form
        String name = request.getParameter("name");
        // luu vao db
        roleModel.create(name);
        // chuyen huong ve trang danh sach user
        response.sendRedirect("/roles");
    }

    public static void renderPageListRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // truyen danh sach users sang JSP
        List<Role> roles = RoleService.getAllRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/WEB-INF/views/roles/list.jsp")
                .forward(req, resp);
    }

    public static void deleteRoleById(HttpServletRequest req,
                                      HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        roleModel.deleteById(id);
        resp.sendRedirect("/roles");
    }

}
