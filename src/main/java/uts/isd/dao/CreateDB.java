package uts.isd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    protected static final String db_url = "jdbc:mysql://localhost:3306/";
    protected static final String db_user = "root";
    protected static final String db_pass = "password123";
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

            // close the connection after loading sample data
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
