<%@ page import="java.util.List" %>
<%@ page import="com.codegym.myapp.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 1/13/26
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");

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
        <h2 class="text-center mt-4">User List</h2>
        <table class="table table-striped mt-4">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <%-- Example static data, replace with dynamic data from server --%>
            <% for (User user : users) { %>
            <tr>
                <th scope="row"><%= user.getId()%></th>
                <td><%= user.getUsername()%></td>
                <td><%= user.getEmail()%></td>
                <td>
                    <a href="/users/edit?id=1" class="btn btn-sm btn-primary">Edit</a>
                    <a href="/users/delete?id=1" class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
            <% } %>
            <%-- Add more user rows as needed --%>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
