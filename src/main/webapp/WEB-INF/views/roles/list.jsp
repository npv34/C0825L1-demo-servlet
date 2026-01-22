<%@ page import="java.util.List" %>
<%@ page import="com.codegym.myapp.entities.User" %>
<%@ page import="com.codegym.myapp.entities.Role" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 1/13/26
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Role> roles = (List<Role>) request.getAttribute("roles");

%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <%@ include file="../layouts/header.jsp"%>
    <div class="col-12 mx-auto">
        <h2 class="text-center mt-4">Role List</h2>
        <div class="d-flex justify-content-between mt-4">
            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#createRoleModal">
                Create
            </button>
        </div>

        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%-- Example static data, replace with dynamic data from server --%>
            <% if (roles == null || roles.isEmpty()) { %>
            <tr>
                <td colspan="4" class="text-center">No roles found.</td>
            </tr>
            <% } else { %>
            <% for (Role role : roles) { %>
            <tr>
                <th scope="row"><%= role.getId()%></th>
                <td><%= role.getName()%></td>
                <td>
                    <a href="/roles/edit?id=<%= role.getId()%>" class="btn btn-sm btn-primary">Edit</a>
                    <a onclick="return confirm('Are you sure?')" href="/roles/delete?id=<%= role.getId()%>" class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="createRoleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <form action="/roles/create" method="post">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create new role</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                    <div class="mb-3">
                        <label for="roleName" class="form-label" >Role Name</label>
                        <input type="text" class="form-control" id="roleName" name="name">
                    </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
