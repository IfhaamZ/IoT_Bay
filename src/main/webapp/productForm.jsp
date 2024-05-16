<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="uts.isd.model.Product" %>
<html>
<head>
    <title>Inventory Management Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffForm.css">
</head>
<body>
    <div class="container">
        <h1>Inventory Management</h1>
        <div class="nav-links">
            <a href="productslist">Display All Products</a>
        </div>
        <div class="form-container">
            <%
                Product p = (Product) request.getAttribute("product");
                boolean isUpdate = p != null;
                String error = (String) request.getAttribute("error");
            %>
            <form action="<%= isUpdate ? "productupdate" : "productinsert" %>" method="post">
                <h2><%= isUpdate ? "Edit Products" : "Add New Product" %></h2>
                <% if (isUpdate) { %>
                    <input type="hidden" name="productID" value="<%= p.getProductID() %>" />
                <% } %>
                <label for="productID">Product ID:</label>
                <input type="text" id="productID" name="productID" value="<%= isUpdate ? p.getProductID() : "" %>" <%= isUpdate ? "readonly" : "" %> required>
                
                <c:if test="${error == 'duplicateID'}">
                    <p class="error-message">Product ID already exists. Please choose a different one.</p>
                </c:if>

                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" value="<%= isUpdate ? p.getName() : "" %>" required>
                
                <label for="description">Description:</label>
                <textarea id="description" name="description"
                style="height: 100px; resize: none; 
                font-family: Arial, Helvetica, sans-serif; 
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;" required><%= isUpdate ? p.getDescription() : "" %></textarea>
                
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= isUpdate ? p.getPrice() : "" %>" pattern="\d+(\.\d{2})?" required>
                
                <label for="stock">Stock Quantity:</label>
                <input type="text" id="stock" name="stock" value="<%= isUpdate ? p.getStockQuantity() : "" %>" pattern="\d+" required>
                
                <label for="category">Category:</label>
                <input type="text" id="category" name="category" value="<%= isUpdate ? p.getCategory() : "" %>" required>

                <label for="supplier">Supplier:</label>
                <input type="text" id="supplier" name="supplier" value="<%= isUpdate ? p.getSupplier() : "" %>" required>
                
                <label for="manuDate">Manufacture Date (YYYY-MM-DD):</label>
                <input type="text" id="manuDate" name="manuDate" value="<%= isUpdate ? p.getManuDate() : "" %>" pattern="\d{4}-\d{2}-\d{2}" required>

                <div class="form-buttons">
                    <input type="submit" value="Save">
                    <button type="button" onclick="window.location.href='displayProducts.jsp';">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
