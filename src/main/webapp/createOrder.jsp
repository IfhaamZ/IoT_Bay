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
    <form action="ordernew" method="post">
      <label for="customerID">Customer ID:</label>
      <input type="text" id="customerID" name="customerID" required /><br />

      <label for="datePlaced">Date Placed:</label>
      <input type="text" id="datePlaced" name="datePlaced" required /><br />

      <label for="status">Status:</label>
      <input type="text" id="status" name="status" required /><br />

      <label for="shippingAddress">Shipping Address:</label>
      <input
        type="text"
        id="shippingAddress"
        name="shippingAddress"
        required
      /><br />

      <label for="billingAddress">Billing Address:</label>
      <input
        type="text"
        id="billingAddress"
        name="billingAddress"
        required
      /><br />

      <label for="createdBy">Created By:</label>
      <input type="text" id="createdBy" name="createdBy" required /><br />

      <label for="createdDate">Created Date:</label>
      <input type="text" id="createdDate" name="createdDate" required /><br />

      <input type="submit" value="Create Order" />
    </form>
    <% if (request.getAttribute("error") != null) { %>
    <p style="color: red"><%= request.getAttribute("error") %></p>
    <% } %>
  </body>
</html>
