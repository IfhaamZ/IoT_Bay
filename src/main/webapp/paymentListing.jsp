<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Payment" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="uts.isd.model.*"%>
<html>
<head>
    <title>IoT Store Application</title>
</head>
<style>
    header {
  display: flex;
  background-color: white;
  overflow: hidden;
  padding: 0.5%;
}

header h1 {
  margin: 0;
  margin-top: 5px;
  padding-left: 50px;
  font-family: Roboto;
}

nav ul {
  list-style-type: none;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

nav ul li {
  padding-inline: 20px;
}

nav ul li a {
  color: #087e8b;
  font-weight: bold;
  text-decoration: none;
  font-size: 1.2rem;
}

nav ul li a:hover {
  color: #ff5a5f;
}
</style>
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
    <center>
        <h1>Payment Management</h1>
        <!-- links to payment management form, listing, and search pages -->

        <h2>
            <a href="welcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="paymentnew">Add New Payment</a>
            &nbsp;&nbsp;&nbsp;
            <a href="paymentlist">List All Payments</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Payment History</h2></caption>
            <!-- categories in each payment listing -->
            <tr>
                <th>ID</th>
                <th>Payment Method</th>
                <th>Gift Card Number</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
                    <!-- getting each value of the payment in the database to view here -->
                    <%
                        ArrayList<Payment> payments = (ArrayList<Payment>) request.getAttribute("listPayments");
                        if (payments != null) {
                            for (Payment p : payments) {
                    %>
                <tr>
                    <td><%= p.getPaymentID() %></td>
                    <td><%= p.getMethod() %></td>
                    <td><%= p.getGCNum() %></td>
                    <td><%= p.getPaymentAmount() %></td>
                    <td><%= p.getPaymentDate() %></td>
                    <td>
                        <a href="paymentedit?paymentID=<%= p.getPaymentID() %>">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="paymentdelete?paymentID=<%= p.getPaymentID() %>">Delete</a>                     
                    </td>
                </tr>
            <%
                            }
                        }
                    %>
        </table>
    </div>   
</body>
</html>