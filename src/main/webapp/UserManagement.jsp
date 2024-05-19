<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
    <center>
        <h1>User Management</h1>
        <h2>
            <!-- links to different sections of website.  -->
            <a href="adminWelcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="/usernew">Add New User</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/userlist">List All Users</a>
            &nbsp;&nbsp;&nbsp;
            <a href="SearchUser.jsp">Search User</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.name}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.status}" /></td>
                    <!-- links leading to different functions.  -->
                    <td>
                        <a href="/useredit?email=<c:out value='${user.email}' />">Edit</a>
                        <br>
                        <a href="/userdelete?email=<c:out value='${user.email}' />">Delete</a>
                        <br>
                        <a href="/useractivate?email=<c:out value='${user.email}' />">Activate</a>    

                        <a href="/userdeactivate?email=<c:out value='${user.email}' />">Deactivate</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>