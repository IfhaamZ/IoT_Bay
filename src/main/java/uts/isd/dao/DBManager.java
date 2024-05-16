package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;

import uts.isd.model.LineItem;
import uts.isd.model.Order;
import uts.isd.model.Product;

public class DBManager {

    private Statement stmt;
    private Connection conn;

    public DBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
    }

    // SystemAdmin
    // public void insertStaff(String username, String name, String email, String password, String position,
    //         String department) throws SQLException {
    //     stmt.executeUpdate("INSERT INTO staff "
    //             + "VALUES (DEFAULT, "
    //             + "'" + username + "',"
    //             + "'" + name + "',"
    //             + "'" + email + "',"
    //             + "'" + password + "',"
    //             + "'" + position + "',"
    //             + "'" + department + "',"
    //             + " TRUE)");
    // }

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
    // public void insertOrder(Order order) throws SQLException {
    //     String query = "INSERT INTO `order` (customerID, orderDate, orderStatus, orderTotal, shippingAddress, billingAddress, createdBy, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    //         pstmt.setInt(1, order.getCustomerID());
    //         pstmt.setString(2, order.getDatePlaced());
    //         pstmt.setString(3, order.getStatus());
    //         pstmt.setFloat(4, order.calculateTotal());
    //         pstmt.setString(5, order.getShippingAddress());
    //         pstmt.setString(6, order.getBillingAddress());
    //         pstmt.setString(7, order.getCreatedBy());
    //         pstmt.setString(8, order.getCreatedDate());
    //         pstmt.executeUpdate();

    //         ResultSet keys = pstmt.getGeneratedKeys();
    //         if (keys.next()) {
    //             int orderID = keys.getInt(1);
    //             for (LineItem item : order.getLineItems()) {
    //                 insertLineItem(orderID, item);
    //             }
    //         }
    //     }
    // }
    public static boolean insertOrder(Order order) throws SQLException {
        String query = "INSERT INTO `order` (customerID, orderDate, orderStatus, orderTotal, shippingAddress, billingAddress, createdBy, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, order.getCustomerID());
            pstmt.setString(2, order.getDatePlaced());
            pstmt.setString(3, order.getStatus());
            pstmt.setFloat(4, order.calculateTotal());
            pstmt.setString(5, order.getShippingAddress());
            pstmt.setString(6, order.getBillingAddress());
            pstmt.setString(7, order.getCreatedBy());
            pstmt.setString(8, order.getCreatedDate());
    
            boolean rowInserted = pstmt.executeUpdate() > 0;
    
            if (rowInserted) {
                try (ResultSet keys = pstmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        int orderID = keys.getInt(1);
                        for (LineItem item : order.getLineItems()) {
                            insertLineItem(orderID, item);
                        }
                    }
                }
            }
    
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // public Order getOrder(int orderID) throws SQLException {
    //     String query = "SELECT * FROM `order` WHERE orderID = ?";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, orderID);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             int customerID = rs.getInt("customerID");
    //             String datePlaced = rs.getString("orderDate");
    //             String status = rs.getString("orderStatus");
    //             float total = rs.getFloat("orderTotal");
    //             String shippingAddress = rs.getString("shippingAddress");
    //             String billingAddress = rs.getString("billingAddress");
    //             String createdBy = rs.getString("createdBy");
    //             String createdDate = rs.getString("createdDate");

    //             Order order = new Order(orderID, datePlaced, status, customerID);
    //             order.setShippingAddress(shippingAddress);
    //             order.setBillingAddress(billingAddress);
    //             order.setCreatedBy(createdBy);
    //             order.setCreatedDate(createdDate);
    //             order.setLineItems(getLineItems(orderID));
    //             return order;
    //         }
    //     }
    //     return null;
    // }
    public Order getOrder(int orderID) throws SQLException {
        String query = "SELECT * FROM `order` WHERE orderID = ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            try (ResultSet rs = pstmt.executeQuery()) {
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
        }
        return null;
    }
    

    // public ArrayList<Order> getOrdersByCustomerID(int customerID) throws SQLException {
    //     String query = "SELECT * FROM `order` WHERE customerID = ?";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, customerID);
    //         ResultSet rs = pstmt.executeQuery();
    //         ArrayList<Order> orders = new ArrayList<>();
    //         while (rs.next()) {
    //             int orderID = rs.getInt("orderID");
    //             String datePlaced = rs.getString("orderDate");
    //             String status = rs.getString("orderStatus");
    //             float total = rs.getFloat("orderTotal");
    //             String shippingAddress = rs.getString("shippingAddress");
    //             String billingAddress = rs.getString("billingAddress");
    //             String createdBy = rs.getString("createdBy");
    //             String createdDate = rs.getString("createdDate");

    //             Order order = new Order(orderID, datePlaced, status, customerID);
    //             order.setShippingAddress(shippingAddress);
    //             order.setBillingAddress(billingAddress);
    //             order.setCreatedBy(createdBy);
    //             order.setCreatedDate(createdDate);
    //             order.setLineItems(getLineItems(orderID));
    //             orders.add(order);
    //         }
    //         return orders;
    //     }
    // }
    public ArrayList<Order> getOrdersByCustomerID(int customerID) throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT orderID, orderDate, orderStatus, orderTotal, shippingAddress, billingAddress, createdBy, createdDate FROM `order` WHERE customerID = ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
        }
        return orders;
    }
    
    // public void updateOrderStatus(int orderID, String status) throws SQLException {
    //     String query = "UPDATE `order` SET orderStatus = ? WHERE orderID = ?";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setString(1, status);
    //         pstmt.setInt(2, orderID);
    //         pstmt.executeUpdate();
    //     }
    // }

    // Update Order Status
public static boolean updateOrderStatus(int orderID, String status) {
    String sql = "UPDATE `order` SET orderStatus = ? WHERE orderID = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, status);
        pstmt.setInt(2, orderID);
        boolean rowUpdated = pstmt.executeUpdate() > 0;
        return rowUpdated;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // public void deleteOrder(int orderID) throws SQLException {
    //     String query = "DELETE FROM `order` WHERE orderID = ?";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, orderID);
    //         pstmt.executeUpdate();
    //     }
    // }
    // Delete Order
    public boolean deleteOrder(int orderID) throws SQLException {
    String sql = "DELETE FROM `order` WHERE orderID = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setInt(1, orderID);
        boolean rowDeleted = pstmt.executeUpdate() > 0;
        return rowDeleted;
    }
}


    // LineItem
    // public void insertLineItem(int orderID, LineItem lineItem) throws SQLException {
    //     String query = "INSERT INTO orderLineItem (orderID, productID, productQuantity, lineItemPrice, productSKU, lineItemStatus) VALUES (?, ?, ?, ?, ?, ?)";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, orderID);
    //         pstmt.setInt(2, lineItem.getProductID());
    //         pstmt.setInt(3, lineItem.getProductQuantity());
    //         pstmt.setFloat(4, lineItem.getLineItemPrice());
    //         pstmt.setString(5, lineItem.getProductSKU());
    //         pstmt.setString(6, lineItem.getLineItemStatus());
    //         pstmt.executeUpdate();
    //     }
    // }
    public static boolean insertLineItem(int orderID, LineItem lineItem) throws SQLException {
        String sql = "INSERT INTO orderLineItem (orderID, productID, productQuantity, lineItemPrice, productSKU, lineItemStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, lineItem.getProductID());
            pstmt.setInt(3, lineItem.getProductQuantity());
            pstmt.setFloat(4, lineItem.getLineItemPrice());
            pstmt.setString(5, lineItem.getProductSKU());
            pstmt.setString(6, lineItem.getLineItemStatus());
            boolean rowInserted = pstmt.executeUpdate() > 0;
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // public ArrayList<LineItem> getLineItems(int orderID) throws SQLException {
    //     String query = "SELECT * FROM orderLineItem WHERE orderID = ?";

    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, orderID);
    //         ResultSet rs = pstmt.executeQuery();
    //         ArrayList<LineItem> lineItems = new ArrayList<>();
    //         while (rs.next()) {
    //             int lineItemID = rs.getInt("lineItemID");
    //             int productID = rs.getInt("productID");
    //             int productQuantity = rs.getInt("productQuantity");
    //             float lineItemPrice = rs.getFloat("lineItemPrice");
    //             String productSKU = rs.getString("productSKU");
    //             String lineItemStatus = rs.getString("lineItemStatus");

    //             LineItem lineItem = new LineItem(lineItemID, orderID, productID, productQuantity, lineItemPrice,
    //                     productSKU, lineItemStatus);
    //             lineItems.add(lineItem);
    //         }
    //         return lineItems;
    //     }
    // }
    public ArrayList<LineItem> getLineItems(int orderID) throws SQLException {
        ArrayList<LineItem> lineItems = new ArrayList<>();
        String query = "SELECT * FROM orderLineItem WHERE orderID = ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, orderID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    LineItem lineItem = new LineItem(
                            rs.getInt("lineItemID"),
                            rs.getInt("orderID"),
                            rs.getInt("productID"),
                            rs.getInt("productQuantity"),
                            rs.getFloat("lineItemPrice"),
                            rs.getString("productSKU"),
                            rs.getString("lineItemStatus"));
                    lineItems.add(lineItem);
                }
            }
        }
        return lineItems;
    }
    

    // public void updateOrder(Order order) throws SQLException {
    //     String sql = "UPDATE `order` SET " +
    //             "orderDate = ?, " +
    //             "orderStatus = ?, " +
    //             "shippingAddress = ?, " +
    //             "billingAddress = ?, " +
    //             "createdBy = ?, " +
    //             "createdDate = ? " +
    //             "WHERE orderID = ?";

    //     try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    //         pstmt.setString(1, order.getDatePlaced());
    //         pstmt.setString(2, order.getStatus());
    //         pstmt.setString(3, order.getShippingAddress());
    //         pstmt.setString(4, order.getBillingAddress());
    //         pstmt.setString(5, order.getCreatedBy());
    //         pstmt.setString(6, order.getCreatedDate());
    //         pstmt.setInt(7, order.getOrderID());

    //         int affectedRows = pstmt.executeUpdate();
    //         if (affectedRows == 0) {
    //             throw new SQLException("Updating order failed, no rows affected.");
    //         }
    //     }
    // }
    public boolean updateOrder(Order order) throws SQLException {
        String sql = "UPDATE `order` SET " +
                "orderDate = ?, " +
                "orderStatus = ?, " +
                "shippingAddress = ?, " +
                "billingAddress = ?, " +
                "createdBy = ?, " +
                "createdDate = ? " +
                "WHERE orderID = ?";
        
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, order.getDatePlaced());
            pstmt.setString(2, order.getStatus());
            pstmt.setString(3, order.getShippingAddress());
            pstmt.setString(4, order.getBillingAddress());
            pstmt.setString(5, order.getCreatedBy());
            pstmt.setString(6, order.getCreatedDate());
            pstmt.setInt(7, order.getOrderID());
            
            boolean rowUpdated = pstmt.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    
}