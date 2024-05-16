<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Product" %>
<html>
<head>
    <title>Product Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productSearchC.css">
</head>
<body>
    <div class="container">
        <h1>IoTBay</h1>
        <br>
        <div class="nav-links">
            <a href="productview">↩️ Return to All Products List</a>
        </div>
        <div class="search-container">
            <h2>Search Products</h2>
            <form action="productsearchC" method="get">
                <input type="hidden" name="action" value="search">
                <b><label for="productName">Product Name:</label></b>
                <input type="text" id="productName" name="productName">
                <b><label for="category">Category:</label></b>
                <input type="text" id="category" name="category">
                &nbsp;&nbsp;&nbsp;
                <input type="submit" value="🔍 Search">
            </form>
        </div>
        <div class="table-container">
            <table>
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
                            <td><%= p.getCategory() %></td>
                            <td><%= p.getSupplier() %></td>
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
