<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Staff Dashboard</title>
    <link rel="stylesheet" href="css/staffWelcome.css">
    <link
      href="https://fonts.googleapis.com/css?family=Roboto"
      rel="stylesheet"
    />
</head>
<body>
    <header>
      <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <h1>TechTide</h1>
      </div>
      <div>
        <nav>
          <ul>
            <li><a href="logout.jsp">Logout</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <div class="container">
        <% User user = (User) session.getAttribute("user"); %>
        <% String name = user.getName(); %>
        <h1>Welcome, <%= name%>!</h1>
        <div class="button-container">
            <button class="nav-button" onclick="location.href='productslist'">Product Management</button>
        </div>
    </div>
</body>
</html>
