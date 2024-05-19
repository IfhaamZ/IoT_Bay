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
            // Create the database if it doesn't already exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + db_name);
            conn.close();

            // Create tables within the newly created database
            Connection con = DriverManager.getConnection(db_url + db_name, db_user, db_pass);
            createTables(con);

            // Close the connection after creating the tables
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        // Create the customer table
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

        // Create the staff table
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

        // Create the product table
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

        // Create the order table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `order` ("
                    + "orderID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "datePlaced DATE NOT NULL,"
                    + "status VARCHAR(50) NOT NULL,"
                    + "customerID INT NOT NULL,"
                    + "shippingAddress VARCHAR(255) NOT NULL,"
                    + "billingAddress VARCHAR(255) NOT NULL,"
                    + "createdBy VARCHAR(255) NOT NULL,"
                    + "createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")");
            System.out.println("Order table creation successful.");
        }

        // Create the orderLineItem table
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

        // Create the payment table
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
