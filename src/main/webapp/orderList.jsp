<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Order List</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/orderList.css"
    />
  </head>
  <body>
    <h2>Order List</h2>
    <div class="nav-links">
      <a href="ordernew">Add New Order</a>
      <a href="orderlist">List All Orders</a>
      <a href="searchOrder.jsp">Search Orders</a>
    </div>
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
          <td>${order.calculateTotal()}</td>
          <td>${order.shippingAddress}</td>
          <td>${order.billingAddress}</td>
          <td>${order.createdBy}</td>
          <td>${order.createdDate}</td>
          <td>
            <a style="color:black;"
              href="orderedit?orderID=${order.orderID}&customerID=${order.customerID}"
              >Edit</a
            >
            <a style="color:black;"
              href="orderdelete?orderID=${order.orderID}&customerID=${order.customerID}"
              onclick="return confirm('Are you sure?')"
              >Delete</a
            >
            <a style="color:black;"
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
