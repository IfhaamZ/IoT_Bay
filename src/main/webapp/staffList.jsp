<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="uts.isd.model.Staff" %>
<%@page import="uts.isd.model.*"%>
<html>
<head>
    <title>Staff Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffList.css">
</head>
<body>
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
        <h1>Staff Management</h1>
        <div class="nav-links">
            <a href="adminWelcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="staffnew">Add New Staff</a>
            <a href="stafflist">List All Staff</a>
            <a href="searchStaff.jsp">Search Staff</a>
        </div>
        <br>
        <div class="table-container">
            <table>
                <caption>List of Staff Members</caption>
                <br>
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Role</th>
                        <th>Department</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Staff> listStaff = (List<Staff>) request.getAttribute("ListOfStaff");
                        if (listStaff != null) {
                            for (Staff staff : listStaff) {
                    %>
                        <tr>
                            <td><%= staff.getEmail() %></td>
                            <td><%= staff.getName() %></td>
                            <td><%= staff.getPhone() %></td>
                            <td><%= staff.getCity() %></td>
                            <td><%= staff.getCountry() %></td>
                            <td><%= staff.getRole() %></td>
                            <td><%= staff.getDepartment() %></td>
                            <td><%= staff.isActive() ? "Active" : "Inactive" %></td>
                            <td>
                                <a href="staffedit?email=<%= staff.getEmail() %>" class="edit">Edit</a>
                                <br>
                                <br>
                                <a href="staffdelete?email=<%= staff.getEmail() %>" class="delete" onclick="return confirm('Are you sure you want to delete this staff member?')">Delete</a>
                                <br>
                                <br>
                                <% if (staff.isActive()) { %>
                                    <a href="staffdeactivate?email=<%= staff.getEmail() %>" class="deactivate">Deactivate</a>
                                <% } else { %>
                                    <a href="staffactivate?email=<%= staff.getEmail() %>" class="activate">Activate</a>
                                <% } %>
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
