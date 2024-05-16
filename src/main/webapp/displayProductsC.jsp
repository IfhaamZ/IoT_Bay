<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uts.isd.model.Product" %>
<%@page import="uts.isd.model.*"%>
<html>
<head>
    <title>IoTBay Catalogue</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productListC.css">
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
        <h1>IoTBay</h1>
        <h2 style="text-align: center;">Start browsing 🌐</h2><br>
        <div class="nav-links">
            <a href="staffWelcome.jsp" style="background: lightgreen;">Go Back</a>
            <a href="productview">👓 Display All Products</a>
            <a href="searchProductC.jsp">🔍 Search Products</a>
            <a href="index.jsp">🏠 Return Home</a>
        </div>
        <% if (request.getAttribute("productview") != null) { %>
        <div class="table-container">
            <table><br>
                <caption>Products Catalogue 📰</caption>
                <thead>
                    <tr>
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
