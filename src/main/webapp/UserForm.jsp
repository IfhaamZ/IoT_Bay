<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <center>
        <h1>User Form</h1>
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
        <c:if test="${user != null}">
            <form action="/userupdate" method="post">
        </c:if>
        <c:if test="${user == null}">
            <form action="/userinsert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${user != null}">
                        Edit User
                    </c:if>
                    <c:if test="${user == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="AccountID" value="<c:out value='${user.id}' />" />
                </c:if>           
            <tr>
                <!-- name  -->
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${user.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <!-- password  -->
                <th>Password: </th>
                <td>
                    <input type="text" name="password" size="45"
                            value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <!-- email  -->
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${user.email}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>