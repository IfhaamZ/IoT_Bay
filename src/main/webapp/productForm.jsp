<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="uts.isd.model.Product" %>
<html>
<head>
    <title>Staff Management Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffForm.css">
</head>
<body>
    <div class="container">
        <h1>Staff Management</h1>
        <div class="nav-links">
            <a href="productnew">Add New Product</a>
            <a href="productslist">List All Products</a>
        </div>
        <div class="form-container">
            <%
                Product p = (Product) request.getAttribute("product");
                boolean isUpdate = p != null;
            %>
            <form action="<%= isUpdate ? "productupdate" : "productinsert" %>" method="post">
                <h2><%= isUpdate ? "Edit Products" : "Add New Product" %></h2>
                <% if (isUpdate) { %>
                    <input type="hidden" name="productID" value="<%= p.getProductID() %>" />
                <% } %>
                <label for="productID">Product ID:</label>
                <input type="text" id="productID" name="productID" value="<%= isUpdate ? p.getProductID() : "" %>" <%= isUpdate ? "readonly" : "" %> required>
                
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="<%= isUpdate ? p.getName() : "" %>" required>
                
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="<%= isUpdate ? p.getDescription() : "" %>" required>
                
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= isUpdate ? p.getPrice() : "" %>" required>
                
                <label for="stock">Stock Quantity:</label>
                <input type="text" id="stock" name="stock" value="<%= isUpdate ? p.getStockQuantity() : "" %>" required>
                
                <label for="supplier">Supplier:</label>
                <input type="text" id="supplier" name="supplier" value="<%= isUpdate ? p.getSupplier() : "" %>" required>
                
                <label for="category">Category:</label>
                <input type="text" id="category" name="category" value="<%= isUpdate ? p.getCategory() : "" %>" required>
                
                <label for="manuDate">Manufacture Date:</label>
                <input type="text" id="manuDate" name="manuDate" value="<%= isUpdate ? p.getManuDate() : "" %>" required>
                
                <div class="form-buttons">
                    <input type="submit" value="Save">
                    <button type="button" onclick="window.location.href='displayProducts.jsp';">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
