<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Product" %>
<html>
<head>
    <title>Product Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffSearch.css">
</head>
<body>
    <div class="container">
        <h1>Inventory Management</h1>
        <div class="nav-links">
            <a href="productnew">Add New Product</a>
            <a href="productslist">Return to All Products List</a>
        </div>
        <br>
        <div class="search-container">
            <h2>Search Products</h2>
            <form action="productsearch" method="get">
                <input type="hidden" name="action" value="search">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName">
                <label for="category">Category:</label>
                <input type="text" id="category" name="category">
                <input type="submit" value="Search">
            </form>
        </div>
        <div class="table-container">
            <table>
                <tbody>
                    <%
                        ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productslist");
                        if (products != null) {
                            for (Product p : products) {
                    %>
                        <tr>
                            <td><%= p.getProductID() %></td>
                            <td><%= p.getName() %></td>
                            <td><%= p.getDescription() %></td>
                            <td><%= p.getPrice() %></td>
                            <td><%= p.getStockQuantity() %></td>
                            <td><%= p.getCategory() %></td>
                            <td><%= p.getSupplier() %></td>
                            <td><%= p.getManuDate() %></td>
                            <td>
                                <a href="productedit?productID=<%= p.getProductID() %>" class="edit">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="productdelete?productID=<%= p.getProductID() %>" class="delete" onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
