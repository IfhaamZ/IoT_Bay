<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Product" %>
<html>
<head>
    <title>Inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffList.css">
</head>
<body>
    <div class="container">
        <h1>Inventory</h1>
        <div class="nav-links">
            <a href="productview">Display All Products</a>
        </div>
        <div class="table-container">
            <table>
                <caption>List of Products</caption>
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock Quantity</th>
                        <th>Category</th>
                        <th>Supplier</th>
                        <th>Manufacture Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productview");
                        if (products != null) {
                            for (Product p : products) {
                    %>
                        <tr>
                            <td><%= p.getProductID() %></td>
                            <td><%= p.getName() %></td>
                            <td><%= p.getDescription() %></td>
                            <td><%= p.getPrice() %></td>
                            <td><%= p.getStockQuantity() %></td>
                            <td><%= p.getSupplier() %></td>
                            <td><%= p.getCategory() %></td>
                            <td><%= p.getManuDate() %></td>
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
