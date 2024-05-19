<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- Page directive specifying the language and content type -->
<%@ page import="uts.isd.model.Order" %>
<!-- Importing Order class -->
<%@ page import="uts.isd.model.LineItem" %>
<!-- Importing LineItem class -->
<%@ page import="java.util.List" %>
<!-- Importing List class -->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <!-- Specifies the character encoding -->
    <title>View Order</title>
    <!-- Page title -->
  </head>
  <body>
    <h2>Order Details</h2>
    <!-- Heading for the page -->
    <% // Retrieve the order object from the session Order order = (Order)
    session.getAttribute("order"); if (order != null) { %>
    <!-- Display order details if order is not null -->
    <p>Order ID: <%= order.getOrderID() %></p>
    <p>Date Placed: <%= order.getDatePlaced() %></p>
    <p>Status: <%= order.getStatus() %></p>
    <p>Shipping Address: <%= order.getShippingAddress() %></p>
    <p>Billing Address: <%= order.getBillingAddress() %></p>
    <p>Created By: <%= order.getCreatedBy() %></p>
    <p>Created Date: <%= order.getCreatedDate() %></p>

    <h3>Line Items</h3>
    <!-- Heading for line items section -->
    <% // Retrieve line items from the order object LineItem[] lineItems =
    order.getLineItems(); if (lineItems != null && lineItems.length > 0) { %>
    <!-- Display each line item if line items are not null or empty -->
    <ul>
      <% for (LineItem item : lineItems) { %>
      <li>
        Product ID: <%= item.getProductID() %>, Quantity: <%= item.getQuantity()
        %>, Price: <%= item.getLineItemPrice() %>
      </li>
      <% } %>
    </ul>
    <% } else { %>
    <!-- Message if no line items are found -->
    <p>No line items found for this order.</p>
    <% } %> <% } else { %>
    <!-- Message if no order details are available -->
    <p>No order details available.</p>
    <% } %>
  </body>
</html>
