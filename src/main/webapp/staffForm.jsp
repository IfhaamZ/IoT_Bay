<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Staff" %>
<html>
<head>
    <title>Staff Management Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffForm.css">
</head>
<body>
    <div class="container">
        <h1>Staff Management</h1>
        <div class="nav-links">
            <a href="staffnew">Add New Staff</a>
            <a href="stafflist">List All Staff</a>
        </div>
        <div class="form-container">
            <%
                Staff staff = (Staff) request.getAttribute("staff");
                boolean isUpdate = staff != null;
            %>
            <form action="<%= isUpdate ? "update" : "staffinsert" %>" method="post">
                <h2><%= isUpdate ? "Edit Staff" : "Add New Staff" %></h2>
                <% if (isUpdate) { %>
                    <input type="hidden" name="email" value="<%= staff.getEmail() %>" />
                <% } %>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" value="<%= isUpdate ? staff.getEmail() : "" %>" <%= isUpdate ? "readonly" : "" %> required>
                
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="<%= isUpdate ? staff.getName() : "" %>" required>
                
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= isUpdate ? staff.getPassword() : "" %>" required>
                
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="<%= isUpdate ? staff.getPhone() : "" %>" required>
                
                <label for="city">City:</label>
                <input type="text" id="city" name="city" value="<%= isUpdate ? staff.getCity() : "" %>" required>
                
                <label for="country">Country:</label>
                <input type="text" id="country" name="country" value="<%= isUpdate ? staff.getCountry() : "" %>" required>
                
                <label for="role">Role:</label>
                <input type="text" id="role" name="role" value="<%= isUpdate ? staff.getRole() : "" %>" required>
                
                <label for="department">Department:</label>
                <input type="text" id="department" name="department" value="<%= isUpdate ? staff.getDepartment() : "" %>" required>
                
                <div class="form-buttons">
                    <input type="submit" value="Save">
                    <button type="button" onclick="window.location.href='staffList.jsp';">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
