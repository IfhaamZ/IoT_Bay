<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!-- Importing JSTL core tag library -->
<html>
  <head>
    <title>Order List</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/orderList.css"
    />
    <!-- Linking external CSS -->
  </head>
  <body>
    <h2>Order List</h2>
    <div class="nav-links">
      <!-- Navigation links for different order management actions -->
      <a href="main.jsp">Go Back</a>
      <a href="ordernew">Add New Order</a>
      <a href="orderlist">List All Orders</a>
      <a href="searchOrder.jsp">Search Orders</a>
    </div>
    <table border="1">
      <!-- Order list table with a border -->
      <tr>
        <!-- Table headers for the order list -->
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
      <!-- Iterating over the list of orders using JSTL forEach tag -->
      <c:forEach var="order" items="${listOrder}">
        <tr>
          <!-- Displaying order details in table columns -->
          <td>${order.orderID}</td>
          <td>${order.customerID}</td>
          <td>${order.datePlaced}</td>
          <td>${order.status}</td>
          <td>${order.calculateTotal()}</td>
          <!-- Calculate and display order total -->
          <td>${order.shippingAddress}</td>
          <td>${order.billingAddress}</td>
          <td>${order.createdBy}</td>
          <td>${order.createdDate}</td>
          <td>
            <!-- Links for edit, delete, and view actions on the order -->
            <a
              style="color: black"
              href="orderedit?orderID=${order.orderID}&customerID=${order.customerID}"
              >Edit</a
            >
            <a
              style="color: black"
              href="orderdelete?orderID=${order.orderID}&customerID=${order.customerID}"
              onclick="return confirm('Are you sure?')"
              >Delete</a
            >
            <a
              style="color: black"
              href="orderview?orderID=${order.orderID}&customerID=${order.customerID}"
              >View</a
            >
          </td>
        </tr>
      </c:forEach>
    </table>
    <br />
    <a href="ordernew">Create New Order</a>
  </body>
</html>
