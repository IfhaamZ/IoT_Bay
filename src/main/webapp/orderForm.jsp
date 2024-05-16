<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Order" %>
<html>
<head>
    <title>Order Management Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderForm.css">
</head>
<body>
    <div class="container">
        <h1>Order Management</h1>
        <div class="nav-links">
            <a href="ordernew">Add New Order</a>
            <a href="orderlist">List All Orders</a>
            <a href="searchOrder.jsp">Search Orders</a>
        </div>
        <div class="form-container">
            <%
                Order order = (Order) request.getAttribute("order");
                boolean isUpdate = order != null;
            %>
            <form action="<%= isUpdate ? "orderupdate" : "orderinsert" %>" method="post">
                <h2><%= isUpdate ? "Edit Order" : "Add New Order" %></h2>
                <% if (isUpdate) { %>
                    <input type="hidden" name="orderID" value="<%= order.getOrderID() %>" />
                <% } %>
                <label for="customerID">Customer ID:</label>
                <input type="text" id="customerID" name="customerID" value="<%= isUpdate ? order.getCustomerID() : "" %>" required>
                
                <label for="datePlaced">Date Placed:</label>
                <input type="text" id="datePlaced" name="datePlaced" value="<%= isUpdate ? order.getDatePlaced() : "" %>" required>
                
                <label for="status">Status:</label>
                <input type="text" id="status" name="status" value="<%= isUpdate ? order.getStatus() : "" %>" required>
                
                <label for="shippingAddress">Shipping Address:</label>
                <input type="text" id="shippingAddress" name="shippingAddress" value="<%= isUpdate ? order.getShippingAddress() : "" %>" required>
                
                <label for="billingAddress">Billing Address:</label>
                <input type="text" id="billingAddress" name="billingAddress" value="<%= isUpdate ? order.getBillingAddress() : "" %>" required>
                
                <label for="createdBy">Created By:</label>
                <input type="text" id="createdBy" name="createdBy" value="<%= isUpdate ? order.getCreatedBy() : "" %>" required>
                
                <label for="createdDate">Created Date:</label>
                <input type="text" id="createdDate" name="createdDate" value="<%= isUpdate ? order.getCreatedDate() : "" %>" required>
                
                <div class="form-buttons">
                    <input type="submit" value="Save">
                    <button type="button" onclick="window.location.href='orderlist';">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
