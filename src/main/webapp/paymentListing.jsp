<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Payment" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>IoT Store Application</title>
</head>
<body>
    <center>
        <h1>Payment Management</h1>
        <h2>
            <a href="paymentnew">Add New Payment</a>
            &nbsp;&nbsp;&nbsp;
            <a href="paymentlist">List All Payments</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Payment History</h2></caption>
            <tr>
                <th>ID</th>
                <th>Payment Method</th>
                <th>Gift Card Number</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
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