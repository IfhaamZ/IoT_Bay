<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="uts.isd.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            
            String storedName = user.getName();
            String storedPassword = user.getPassword();
            
        %>

        <form action="login.jsp" method="post">
                <label for="name">Name:</label>
                <input name="name" id="name" placeholder="name" />
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" placeholder="password" />
                <input type="hidden" name="login" id="login" value="true" />
                <button type="submit">Login</button>
        </form>
        
        <% 
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            if (name != null && password != null && storedName.equals(name) && storedPassword.equals(password)) {
                response.sendRedirect("welcome.jsp");
            } else {
            %>
                <p>Please enter a valid name or password.</p>
                <br>
                <p> If you have not registered yet, please do so.</p>
                <button><a href="/register.jsp">Registration</a></button>
            <%
                }
            %>

    </body>
</html>