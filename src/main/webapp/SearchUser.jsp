<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="uts.isd.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
    <center>
        <h1>User Management</h1>
        <h2>
            <a href="adminWelcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="/usernew">Add New User</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/userlist">List All Users</a>
            &nbsp;&nbsp;&nbsp;
            <a href="SearchUser.jsp">Search User</a>
        </h2>
    </center>
    <div align="center">
        <div class="search-container">
            <h2>Search User Members</h2>
            <!-- search user function from user servlet -->
            <form action="usersearch" method="get">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name">
                &nbsp;&nbsp;&nbsp;
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone">
                &nbsp;&nbsp;&nbsp;
                <input type="submit" value="Search">
            </form>
        </div>
        <br>
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>Name</th>
                <th>Phone</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.phone}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>