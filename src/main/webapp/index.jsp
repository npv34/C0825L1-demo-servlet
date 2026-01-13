<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  // code ma java
    String greeting = "Welcome to JSP!";
%>
<%!
    // method
    public String getCurrentTime() {
        java.util.Date date = new java.util.Date();
        return date.toString();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<p><%= getCurrentTime() %></p>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>