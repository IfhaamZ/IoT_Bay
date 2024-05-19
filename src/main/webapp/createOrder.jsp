<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Create Order</title>
  </head>
  <body>
    <h2>Create Order</h2>
    <!-- Form for creating a new order, posting to the 'ordernew' servlet -->
    <form action="ordernew" method="post">
      <label for="customerID">Customer ID:</label>
      <!-- Input field for customer ID, required -->
      <input type="text" id="customerID" name="customerID" required /><br />

      <label for="datePlaced">Date Placed:</label>
      <!-- Input field for date placed, required -->
      <input type="text" id="datePlaced" name="datePlaced" required /><br />

      <label for="status">Status:</label>
      <!-- Input field for status, required -->
      <input type="text" id="status" name="status" required /><br />

      <label for="shippingAddress">Shipping Address:</label>
      <!-- Input field for shipping address, required -->
      <input
        type="text"
        id="shippingAddress"
        name="shippingAddress"
        required
      /><br />

      <label for="billingAddress">Billing Address:</label>
      <!-- Input field for billing address, required -->
      <input
        type="text"
        id="billingAddress"
        name="billingAddress"
        required
      /><br />

      <label for="createdBy">Created By:</label>
      <!-- Input field for created by, required -->
      <input type="text" id="createdBy" name="createdBy" required /><br />

      <label for="createdDate">Created Date:</label>
      <!-- Input field for created date, required -->
      <input type="text" id="createdDate" name="createdDate" required /><br />

      <!-- Submit button to create the order -->
      <input type="submit" value="Create Order" />
    </form>

    <!-- Display error message if 'error' attribute is present in the request -->
    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red"><%= request.getAttribute("error") %></p>
    <% } %>
  </body>
</html>
