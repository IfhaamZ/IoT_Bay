package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.*;

public class DBManager {
    
    private Statement stmt;
    
    public DBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
    }

    // Staff
    // Create
    public static boolean insertStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (email, name, password, phone, city, country, role, department, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, staff.getEmail());
            st.setString(2, staff.getName());
            st.setString(3, staff.getPassword());
            st.setString(4, staff.getPhone());
            st.setString(5, staff.getCity());
            st.setString(6, staff.getCountry());
            st.setString(7, staff.getRole());
            st.setString(8, staff.getDepartment());
            st.setBoolean(9, staff.isActive());
            boolean rowInserted = st.executeUpdate() > 0;
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Read
    public ArrayList<Staff> fetchStaff() throws SQLException {
        ArrayList<Staff> ListOfStaff = new ArrayList<>();
        String sql = "SELECT email, name, password, phone, city, country, role, department, status FROM staff";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("role"),
                        rs.getString("department"),
                        rs.getBoolean("status"));
                ListOfStaff.add(staff);
            }
        }
        return ListOfStaff;
    }

    // Search staff by name, role and department
     public List<Staff> searchStaff(String name, String role, String department) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT email, name, password, phone, city, country, role, department, status FROM staff WHERE name LIKE ? AND role LIKE ? AND department LIKE ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + department + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Staff staff = new Staff(
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("country"),
                            rs.getString("role"),
                            rs.getString("department"),
                            rs.getBoolean("status"));
                    staffList.add(staff);
                }
            }
        }
        return staffList;
    }

    // Get Staff by Email
    public Staff getStaff(String email) throws SQLException {
        String sql = "SELECT email, name, password, phone, city, country, role, department, status FROM staff WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Staff(
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getString("phone"),
                            rs.getString("city"),
                            rs.getString("country"),
                            rs.getString("role"),
                            rs.getString("department"),
                            rs.getBoolean("status"));
                }
            }
        }
        return null;
    }
    
    // Update
    public static boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET name = ?, password = ?, phone = ?, city = ?, country = ?, role = ?, department = ?, status = ? WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, staff.getName());
            st.setString(2, staff.getPassword());
            st.setString(3, staff.getPhone());
            st.setString(4, staff.getCity());
            st.setString(5, staff.getCountry());
            st.setString(6, staff.getRole());
            st.setString(7, staff.getDepartment());
            st.setBoolean(8, staff.isActive());
            st.setString(9, staff.getEmail());
            boolean rowUpdated = st.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Activate staff
    public static boolean activateStaff(String email) throws SQLException {
        String sql = "UPDATE staff SET status = true WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Deactivate staff
    public static boolean deactivateStaff(String email) throws SQLException {
        String sql = "UPDATE staff SET status = false WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Delete Staff
    public boolean deleteStaff(String email) throws SQLException {
        String sql = "DELETE FROM staff WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            boolean rowDeleted = st.executeUpdate() > 0;
            return rowDeleted;
        }
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

    //Customer

    //Order
    
    //LineItem

    }
