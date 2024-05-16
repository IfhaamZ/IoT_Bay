<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
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
                <th>OrderID</th>
                <th>Payment Method</th>
                <th>Card Number</th>
                <th>Expiry Month</th>
                <th>Expiry Year</th>
                <th>CVN</th>
                <th>Gift Card Number</th>
                <th>PIN</th>
                <th>Amount</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="payment" items="${listPayments}">
                <tr>
                    <td><c:out value="${payment.paymentID}" /></td>
                    <!-- Order ID  -->
                    <td></td>
                    <!-- Order ID -->
                    <td><c:out value="${payment.method}" /></td>
                    <td><c:out value="${payment.cardNum}" /></td>
                    <td><c:out value="${payment.expMonth}" /></td>
                    <td><c:out value="${payment.expYear}" /></td>
                    <td><c:out value="${payment.cvn}" /></td>
                    <td><c:out value="${payment.GCNum}" /></td>
                    <td><c:out value="${payment.pin}" /></td>
                    <td><c:out value="${payment.paymentAmount}" /></td>
                    <td><c:out value="${payment.paymentDate}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${payment.paymentID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${payment.paymentID}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>