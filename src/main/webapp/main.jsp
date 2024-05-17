<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/main.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Main</title>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
<% String name = (user != null) ? user.getName() : ""; %>
<header>
    <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <h1>TechTide</h1>
    </div>
    <div>
        <nav>
            <ul>
                <% if (user != null) { %>
                    <li><a href="logout.jsp">Logout</a></li>
                    <li><%=name %></li>
                <% } else { %>
                    <li><a href="register.jsp">Register</a></li>
                    <li><a href="index.jsp">Home</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>
<div class="container">
    <h2>Dashboard</h2>
    <div class="button-container">
        <button class="nav-button" onclick="location.href='orderlist'">Order Management</button>
        <button class="nav-button" onclick="location.href='paymentlist'">Payment Management</button>
        <button class="nav-button" onclick="location.href='displayProductsC.jsp'">Display Product</button>
    </div>
</div>
</body>
</html>
