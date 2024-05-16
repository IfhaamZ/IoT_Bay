<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Product" %>
<%@page import="uts.isd.model.*"%>
<html>
<head>
    <title>Inventory Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffList.css">
    <style>
        .lowstock-popup {
            background-color: #f44336;
            color: white;
            padding: 5px;
            border-radius: 5px;
        }
    </style>
    <script>
        function togglePopup(stockElement, popupElement) {
            if (parseInt(stockElement.textContent) < 10) {
                popupElement.style.display = 'inline';
            } else {
                popupElement.style.display = 'none';
            }
        }
    </script>
</head>
<body>
<body>
<% User user = (User) session.getAttribute("user"); %>
<% String name = (user != null) ? user.getName() : ""; %>
<header>
    <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <h1>TechTide</h1>
    </div>
    <div>
        <nav>
            <ul>
                <% if (user != null) { %>
                    <li><a href="logout.jsp">Logout</a></li>
                    <li><%=name %></li>
                <% } else { %>
                    <li><a href="register.jsp">Register</a></li>
                    <li><a href="index.jsp">Home</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>
    <div class="container">
        <h1>Inventory Management Options</h1>
        <br>
        <div class="nav-links">
            <a href="staffWelcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="productnew">➕ Add New Product</a>
            <a href="productslist">👓 Display All Products</a>
            <a href="searchProduct.jsp">🔍 Search Products</a>
        </div>
        <% if (request.getAttribute("productslist") != null) { %>
        <div class="table-container">
            <table>
                <br>
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
                        <th>Actions</th>
                    </tr>
                </thead>
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
                            <td>AU$<%= p.getPrice() %></td>
                            <td><span id="stock_<%= p.getProductID() %>"><%= p.getStockQuantity() %></span>&nbsp;&nbsp;
                                <span class="lowstock-popup" id="popup_<%= p.getProductID() %>">Low Stock 📉</span>
                                <script>
                                    togglePopup(document.getElementById('stock_<%= p.getProductID() %>'), document.getElementById('popup_<%= p.getProductID() %>'));
                                </script></td>
                            <td><%= p.getCategory() %></td>
                            <td><%= p.getSupplier() %></td>
                            <td><%= p.getManuDate() %></td>
                            <td>
                                <a href="productedit?productID=<%= p.getProductID() %>" class="edit">✏️ Edit</a>
                                <br>
                                <br>
                                <a href="productdelete?productID=<%= p.getProductID() %>" class="delete" onclick="return confirm('Are you sure you want to delete this product?')">🗑️ Delete</a>
                                <br>
                                <br>
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <% } %>
    </div>
</body>
</html>
