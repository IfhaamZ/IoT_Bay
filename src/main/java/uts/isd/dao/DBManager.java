package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;

import uts.isd.model.LineItem;
import uts.isd.model.Order;
import uts.isd.model.Product;


public class DBManager {

    private Statement stmt;

    public DBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
    }

    // SystemAdmin
    public void insertStaff(String username, String name, String email, String password, String position,
            String department) throws SQLException {
        stmt.executeUpdate("INSERT INTO staff "
                + "VALUES (DEFAULT, "
                + "'" + username + "',"
                + "'" + name + "',"
                + "'" + email + "',"
                + "'" + password + "',"
                + "'" + position + "',"
                + "'" + department + "',"
                + " TRUE)");
    }

    // Product
    public ArrayList<Product> displayAllProducts() throws SQLException {
        ResultSet result = stmt.executeQuery("SELECT * FROM product");
        ArrayList<Product> products = new ArrayList<>();
        while (result.next()) {
            String productID = result.getString("productID");
            String productName = result.getString("productName");
            String description = result.getString("description");
            float price = result.getFloat("price");
            int stock = result.getInt("stock");
            String category = result.getString("category");
            String supplier = result.getString("supplier");
            Date manuDate = result.getDate("manuDate");
            products.add(new Product(productID, productName, description, price, stock, category, supplier, manuDate));
        }
        return products;
    }
    // Staff

    // Customer

    // Order
    public void insertOrder(Order order) throws SQLException {
        String query = "INSERT INTO `order` (customerID, orderDate, orderStatus, orderTotal, shippingAddress, billingAddress, createdBy, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getCustomerID());
            pstmt.setString(2, order.getDatePlaced());
            pstmt.setString(3, order.getStatus());
            pstmt.setFloat(4, order.calculateTotal());
            pstmt.setString(5, order.getShippingAddress());
            pstmt.setString(6, order.getBillingAddress());
            pstmt.setString(7, order.getCreatedBy());
            pstmt.setString(8, order.getCreatedDate());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int orderID = keys.getInt(1);
                for (LineItem item : order.getLineItems()) {
                    insertLineItem(orderID, item);
                }
            }
        }
    }

    public Order getOrder(int orderID) throws SQLException {
        String query = "SELECT * FROM `order` WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt("customerID");
                String datePlaced = rs.getString("orderDate");
                String status = rs.getString("orderStatus");
                float total = rs.getFloat("orderTotal");
                String shippingAddress = rs.getString("shippingAddress");
                String billingAddress = rs.getString("billingAddress");
                String createdBy = rs.getString("createdBy");
                String createdDate = rs.getString("createdDate");

                Order order = new Order(orderID, datePlaced, status, customerID);
                order.setShippingAddress(shippingAddress);
                order.setBillingAddress(billingAddress);
                order.setCreatedBy(createdBy);
                order.setCreatedDate(createdDate);
                order.setLineItems(getLineItems(orderID));
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrdersByCustomerID(int customerID) throws SQLException {
        String query = "SELECT * FROM `order` WHERE customerID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Order> orders = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                String datePlaced = rs.getString("orderDate");
                String status = rs.getString("orderStatus");
                float total = rs.getFloat("orderTotal");
                String shippingAddress = rs.getString("shippingAddress");
                String billingAddress = rs.getString("billingAddress");
                String createdBy = rs.getString("createdBy");
                String createdDate = rs.getString("createdDate");

                Order order = new Order(orderID, datePlaced, status, customerID);
                order.setShippingAddress(shippingAddress);
                order.setBillingAddress(billingAddress);
                order.setCreatedBy(createdBy);
                order.setCreatedDate(createdDate);
                order.setLineItems(getLineItems(orderID));
                orders.add(order);
            }
            return orders;
        }
    }

    public void updateOrderStatus(int orderID, String status) throws SQLException {
        String query = "UPDATE `order` SET orderStatus = ? WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, orderID);
            pstmt.executeUpdate();
        }
    }

    public void deleteOrder(int orderID) throws SQLException {
        String query = "DELETE FROM `order` WHERE orderID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            pstmt.executeUpdate();
        }
    }

    // LineItem
    public void insertLineItem(int orderID, LineItem lineItem) throws SQLException {
        String query = "INSERT INTO orderLineItem (orderID, productID, productQuantity, lineItemPrice, productSKU, lineItemStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, lineItem.getProductID());
            pstmt.setInt(3, lineItem.getProductQuantity());
            pstmt.setFloat(4, lineItem.getLineItemPrice());
            pstmt.setString(5, lineItem.getProductSKU());
            pstmt.setString(6, lineItem.getLineItemStatus());
            pstmt.executeUpdate();
        }
    }

    public ArrayList<LineItem> getLineItems(int orderID) throws SQLException {
        String query = "SELECT * FROM orderLineItem WHERE orderID = ?";
        Connection conn;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<LineItem> lineItems = new ArrayList<>();
            while (rs.next()) {
                int lineItemID = rs.getInt("lineItemID");
                int productID = rs.getInt("productID");
                int productQuantity = rs.getInt("productQuantity");
                float lineItemPrice = rs.getFloat("lineItemPrice");
                String productSKU = rs.getString("productSKU");
                String lineItemStatus = rs.getString("lineItemStatus");

                LineItem lineItem = new LineItem(lineItemID, orderID, productID, productQuantity, lineItemPrice,
                        productSKU, lineItemStatus);
                lineItems.add(lineItem);
            }
            return lineItems;
        }
    }

    // LineItem

}