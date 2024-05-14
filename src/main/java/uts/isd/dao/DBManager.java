package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.Product;


public class DBManager {
<<<<<<< Updated upstream
    
    private Statement stmt;
    
    public DBManager(Connection conn) throws SQLException{
        stmt = conn.createStatement();
=======
    // Staff
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
>>>>>>> Stashed changes
    }
    // SystemAdmin
    public void insertStaff(String username, String name, String email, String password, String position, String department) throws SQLException {
        stmt.executeUpdate("INSERT INTO staff "
        + "VALUES (DEFAULT, "
        + "'" + username + "',"
        + "'" + name + "',"
        + "'" + email + "',"
        + "'" + password + "',"
        + "'" + position + "',"
        + "'" + department + "',"
        + " TRUE)"
        );
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
    //Staff

    //Customer

    //Order
    
    //LineItem

    }
