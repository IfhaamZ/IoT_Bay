<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="uts.isd.model.Order" %> <%@ page
import="uts.isd.model.LineItem" %> <%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>View Order</title>
  </head>
  <body>
    <h2>Order Details</h2>
    <% Order order = (Order) session.getAttribute("order"); if (order != null) {
    %>
    <p>Order ID: <%= order.getOrderID() %></p>
    <p>Date Placed: <%= order.getDatePlaced() %></p>
    <p>Status: <%= order.getStatus() %></p>
    <p>Shipping Address: <%= order.getShippingAddress() %></p>
    <p>Billing Address: <%= order.getBillingAddress() %></p>
    <p>Created By: <%= order.getCreatedBy() %></p>
    <p>Created Date: <%= order.getCreatedDate() %></p>
    <h3>Line Items</h3>
    <% LineItem[] lineItems = order.getLineItems(); if (lineItems != null &&
    lineItems.length > 0) { %>
    <ul>
      <% for (LineItem item : lineItems) { %>
      <li>
        Product ID: <%= item.getProductID() %>, Quantity: <%= item.getQuantity()
        %>, Price: <%= item.getLineItemPrice() %>
      </li>
      <% } %>
    </ul>
    <% } else { %>
    <p>No line items found for this order.</p>
    <% } %> <% } else { %>
    <p>No order details available.</p>
    <% } %>
  </body>
</html>
