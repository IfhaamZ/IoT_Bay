package uts.isd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    protected static final String db_url = "jdbc:mysql://localhost:3306/";
    protected static final String db_user = "root";
    protected static final String db_pass = "Lama1234!";
    protected static final String db_name = "ibdb";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass);
                Statement stmt = conn.createStatement();) {
            // create database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + db_name);
            conn.close();

            // create tables
            Connection con = DriverManager.getConnection(db_url + db_name, db_user, db_pass);
            createTables(con);

            // Load sample data
            loadTables(con);

            // close the connection after loading sample data
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Sample data for customer table
            stmt.executeUpdate(
                    "INSERT INTO customer (username, name, email, password, address, phoneNum, newsletterSub) VALUES "
                            + "('alice_smith', 'Alice Smith', 'alice.smith@example.com', 'password123', '789 Maple St', '1234567890', TRUE),"
                            + "('bob_johnson', 'Bob Johnson', 'bob.johnson@example.com', 'password123', '101 Pine St', '0987654321', FALSE)");

            // Sample data for product table (Assuming productID 1 and 2 exist)
            stmt.executeUpdate(
                    "INSERT INTO product (productID, productName, description, price, stock, category, supplier, manuDate) VALUES "
                            + "('P003', 'Smart Lock', 'WiFi enabled door lock', 75.99, 50, 'Security', 'HomeSecure Inc.', '2023-03-01'),"
                            + "('P004', 'Smart Camera', 'WiFi enabled security camera', 150.00, 30, 'Security', 'HomeSecure Inc.', '2023-04-01')");

            // Sample data for order table
            stmt.executeUpdate(
                    "INSERT INTO `order` (customerID, orderDate, orderStatus, orderTotal, shippingAddress, billingAddress, orderShippedDate, orderDeliveredDate, createdBy, createdDate) VALUES "
                            + "(1, '2024-05-01', 'Pending', 225.00, '789 Maple St', '789 Maple St', NULL, NULL, 'alice_smith', '2024-05-01'),"
                            + "(2, '2024-05-02', 'Shipped', 150.00, '101 Pine St', '101 Pine St', '2024-05-05', NULL, 'bob_johnson', '2024-05-02')");

            // Sample data for orderLineItem table (Assuming orderID 1 and 2, productID 3
            // and 4 exist)
            stmt.executeUpdate(
                    "INSERT INTO orderLineItem (orderID, productID, productQuantity, lineItemPrice, productSKU, lineItemStatus) VALUES "
                            + "(1, 3, 2, 75.99, 'SKU003', 'Pending'),"
                            + "(1, 4, 1, 150.00, 'SKU004', 'Pending'),"
                            + "(2, 3, 2, 75.99, 'SKU003', 'Shipped')");

            // Sample data for payment table (Assuming orderID 1 and 2 exist)
            stmt.executeUpdate(
                    "INSERT INTO payment (orderID, paymentMethod, paymentDate, paymentStatus, paymentAmount, paymentConfNo) VALUES "
                            + "(1, 'Credit Card', '2024-05-01', 'Paid', 225.00, 'CONF789101'),"
                            + "(2, 'Credit Card', '2024-05-02', 'Pending', 150.00, 'CONF789102')");

            System.out.println("Sample data insertion successful.");

            // System.out.println("Customer table creation successful.");
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        // create customer table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS customer ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE,"
                    + "name VARCHAR(30),"
                    + "email VARCHAR(30) UNIQUE,"
                    + "password VARCHAR(100),"
                    + "address VARCHAR(300),"
                    + "phoneNum VARCHAR(20),"
                    + "newsletterSub BOOLEAN"
                    + ")");
            System.out.println("Customer table creation successful.");
        }
        // create staff table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS staff ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE,"
                    + "name VARCHAR(30),"
                    + "email VARCHAR(30) UNIQUE,"
                    + "password VARCHAR(100),"
                    + "position VARCHAR(20),"
                    + "department VARCHAR(20)"
                    + ")");
            System.out.println("Staff table creation successful.");
        }
        // create product table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS product ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "productID VARCHAR(50),"
                    + "productName VARCHAR(50),"
                    + "description VARCHAR(350),"
                    + "price DECIMAL(7, 2),"
                    + "stock INT(10),"
                    + "category VARCHAR(20),"
                    + "supplier VARCHAR(30),"
                    + "manuDate DATE"
                    + ")");
            System.out.println("Product table creation successful.");
        }
        // create order table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `order` ("
                    + "orderID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "customerID INT(10),"
                    + "orderDate DATE,"
                    + "orderStatus VARCHAR(10),"
                    + "orderTotal DECIMAL(7, 2),"
                    + "shippingAddress VARCHAR(300),"
                    + "billingAddress VARCHAR(300),"
                    + "orderShippedDate DATE,"
                    + "orderDeliveredDate DATE,"
                    + "createdBy VARCHAR(30),"
                    + "createdDate DATE"
                    + ")");
            System.out.println("Order table creation successful.");
        }
        // create OrderLineItem table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS orderLineItem ("
                    + "lineItemID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "orderID INT(10),"
                    + "productID INT(10),"
                    + "productQuantity INT(10),"
                    + "lineItemPrice DECIMAL(7, 2),"
                    + "productSKU VARCHAR(50),"
                    + "lineItemStatus VARCHAR(10)"
                    + ")");
            System.out.println("OrderLineItem table creation successful.");
        }
        // create payment table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS payment ("
                    + "paymentID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "orderID INT(10),"
                    + "paymentMethod VARCHAR(50),"
                    + "paymentDate DATE,"
                    + "paymentStatus VARCHAR(10),"
                    + "paymentAmount DECIMAL(7, 2),"
                    + "paymentConfNo VARCHAR(20) UNIQUE"
                    + ")");
            System.out.println("Payment table creation successful.");
        }
    }
}
