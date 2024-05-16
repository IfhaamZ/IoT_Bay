<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="css/staffWelcome.css">
</head>
<body>
    <div class="container">
        <% User user = (User) session.getAttribute("user"); %>
        <% String name = user.getName(); %>
        <h1>Welcome, <%= name%>!</h1>
        <div class="button-container">
            <button class="nav-button" onclick="location.href='productlist'">Product Management</button>
            <button class="nav-button" onclick="location.href='userlist'">User Management</button>
        </div>
    </div>
</body>
</html>
