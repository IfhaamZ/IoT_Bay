<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order List</title>
</head>
<body>
<h2>Order List</h2>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Order Date</th>
        <th>Order Status</th>
        <th>Order Total</th>
        <th>Shipping Address</th>
        <th>Billing Address</th>
        <th>Created By</th>
        <th>Created Date</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="order" items="${listOrder}">
        <tr>
            <td>${order.orderID}</td>
            <td>${order.customerID}</td>
            <td>${order.datePlaced}</td>
            <td>${order.status}</td>
            <td>${order.orderTotal}</td>
            <td>${order.shippingAddress}</td>
            <td>${order.billingAddress}</td>
            <td>${order.createdBy}</td>
            <td>${order.createdDate}</td>
            <td>
                <a href="orderedit?orderID=${order.orderID}">Edit</a>
                <a href="orderdelete?orderID=${order.orderID}" onclick="return confirm('Are you sure?')">Delete</a>
                <a href="orderview?orderID=${order.orderID}">View</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="ordernew">Create New Order</a>
</body>
</html>
