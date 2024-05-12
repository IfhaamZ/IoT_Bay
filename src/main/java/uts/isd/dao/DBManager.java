package uts.isd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
    public static void insertStaff(String username, String name, String email, String password, String position, String department) {
        String sql = "INSERT INTO staff (username, name, email, password, position, department) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, position);
            statement.setString(6, department);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void displayProducts() {
        String sql = "SELECT * FROM product";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                System.out.println("Database ID: " + resultSet.getInt("id"));
                System.out.println("Product ID: " + resultSet.getString("productID"));
                System.out.println("Product Name: " + resultSet.getString("productName"));
                System.out.println("Description: " + resultSet.getString("description"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println("Stock: " + resultSet.getInt("stock"));
                System.out.println("Category: " + resultSet.getString("category"));
                System.out.println("Supplier: " + resultSet.getString("supplier"));
                System.out.println("Manufacture Date: " + resultSet.getDate("manuDate"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}