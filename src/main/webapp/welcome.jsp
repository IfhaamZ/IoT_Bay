<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.isd.model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <% 
        User user = (User) session.getAttribute("user");
        String favcol = user.getFavCol();
    %>
    <body bgcolor="<%= favcol%>" >
        <%
            String email = user.getEmail();
            String name = user.getName();
        %>
        <h1>Welcome</h1>
        <h2>Email: <%= email%></h2>
        <h2>Name: <%= name%></h2>     

    </body>
</html>
