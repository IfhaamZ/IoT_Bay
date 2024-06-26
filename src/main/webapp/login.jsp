<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="uts.isd.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css"/>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    String storedName = (user != null) ? user.getName() : null;
    String storedPassword = (user != null) ? user.getPassword() : null;
    String role = (user != null) ? user.getRole() : null;

    String name = request.getParameter("name");
    String password = request.getParameter("password");

    boolean loginFailed = (name != null && password != null) &&
            (storedName == null || !storedName.equals(name) || !storedPassword.equals(password));

    if (!loginFailed && name != null && password != null) {
        session.setAttribute("user", user); // Make sure the user is in the session
        if (role != null) {
            switch (role) {
                case "admin":
                    response.sendRedirect("adminWelcome.jsp");
                    break;
                case "staff":
                    response.sendRedirect("staffWelcome.jsp");
                    break;
                case "customer":
                    response.sendRedirect("welcome.jsp");
                    break;
                default:
                    loginFailed = true;
            }
        } else {
            loginFailed = true;
        }
    }
%>
<div class="container">
    <form method="post" class="login-form">
        <h2>Login</h2>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" required placeholder="Enter your name"/>

        <label for="password">Password</label>
        <input type="password" name="password" id="password" required placeholder="Enter your password"/>

        <input type="hidden" name="login" id="login" value="true"/>
        <button class="login-form" type="submit">Login</button>
    </form>

    <% if (loginFailed) { %>
    <p class="message">Please enter a valid name or password.</p>
    <p class="message">If you have not registered yet, please do so.</p>
    <button class="link-button">
        <a href="register.jsp">Registration</a>
    </button>
    <% } %>
</div>
</body>
</html>
