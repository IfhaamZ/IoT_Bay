<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="uts.isd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            
            String storedName = (user != null) ? user.getName() : null;
            String storedPassword = (user != null) ? user.getPassword() : null;
            
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            boolean loginFailed = (name != null && password != null) && 
                                 (storedName == null || !storedName.equals(name) || !storedPassword.equals(password));
        %>

        <form method="post">
                <label for="name">Name:</label>
                <input name="name" id="name" placeholder="name" />
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" placeholder="password" />
                <input type="hidden" name="login" id="login" value="true" />
                <button type="submit">Login</button>
        </form>
        
        <% 
            if (loginFailed) {
            %>
                <p>Please enter a valid name or password.</p>
                <br>
                <p> If you have not registered yet, please do so.</p>
                <button><a href="/register.jsp">Registration</a></button>
            <%
            } else if (name != null && password != null) {
                response.sendRedirect("welcome.jsp");
            }
            %>

    </body>
</html> 