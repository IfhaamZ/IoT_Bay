<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- Specifies the page directive with content type and encoding -->
<html>
  <head>
    <title>Search Orders</title>
    <!-- Page title -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/orderSearch.css"
    />
    <!-- Linking external CSS for styling -->
  </head>
  <body>
    <h2>Search Orders</h2>
    <!-- Heading for the page -->
    <!-- Form to search orders by customer ID -->
    <form action="ordersearch" method="get">
      Customer ID: <input type="text" name="customerID" /><br />
      <!-- Input field for customer ID -->
      <input type="submit" value="Search" />
      <!-- Submit button for the search form -->
    </form>

    <!-- Conditional block to display search results if listOrder is not empty -->
    <c:if test="${not empty listOrder}">
      <h2>Search Results</h2>
      <!-- Heading for search results -->
      <table border="1">
        <!-- Table to display the search results -->
        <tr>
          <!-- Table headers for search results -->
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
            <td>${order.orderTotal}</td>
            <td>${order.shippingAddress}</td>
            <td>${order.billingAddress}</td>
            <td>${order.createdBy}</td>
            <td>${order.createdDate}</td>
            <td>
              <!-- Links for edit and delete actions on the order -->
              <a href="orderedit?orderID=${order.orderID}">Edit</a>
              <a
                href="orderdelete?orderID=${order.orderID}"
                onclick="return confirm('Are you sure?')"
                >Delete</a
              >
            </td>
          </tr>
        </c:forEach>
      </table>
    </c:if>
  </body>
</html>
