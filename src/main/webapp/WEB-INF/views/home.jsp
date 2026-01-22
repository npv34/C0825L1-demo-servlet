<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 1/13/26
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <%@ include file="./layouts/header.jsp"%>
    <div class="col-12 mx-auto">
        <h2 class="text-center mt-4">Home Page</h2>
        <p class="text-center mt-4">Welcome to the Home Page!</p>
    </div>
    <!-- Dashboard total user-->
    <div class="row mt-4">
        <div class="col-md-3">
            <div class="card text-white bg-primary mb-3">
                <div class="card-body">
                    <h5 class="card-title">Total Users</h5>
                    <p class="card-text">
                        <% Integer totalUsers = (Integer) request.getAttribute("userCount"); %>
                        <%= totalUsers != null ? totalUsers : 0 %>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- Logs -->
    <div class="row mt-4">
        <div class="col-12">
            <h3>Recent Logs</h3>
            <table class="table table-striped mt-2">
                <thead>
                <tr>
                    <th scope="col">Timestamp</th>
                    <th scope="col">Message</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
