<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="uts.isd.model.Staff" %>
<html>
<head>
    <title>Staff Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffSearch.css">
</head>
<body>
    <div class="container">
        <h1>Staff Management</h1>
        <div class="nav-links">
            <a href="staffnew">Add New Staff</a>
            <a href="stafflist">List All Staff</a>
            <a href="searchStaff.jsp">Search Staff</a>
        </div>
        <br>
        <div class="search-container">
            <h2>Search Staff Members</h2>
            <form action="staffsearch" method="get">
                <input type="hidden" name="action" value="search">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name">
                <label for="role">Role:</label>
                <input type="text" id="role" name="role">
                <label for="department">Department:</label>
                <input type="text" id="department" name="department">
                <input type="submit" value="Search">
            </form>
        </div>
        <div class="table-container">
            <table>
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
                            <td>
                                <a href="staffedit?email=<%= staff.getEmail() %>" class="edit">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="staffdelete?email=<%= staff.getEmail() %>" class="delete" onclick="return confirm('Are you sure you want to delete this staff member?')">Delete</a>
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
