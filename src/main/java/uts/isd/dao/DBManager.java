package uts.isd.dao;

import java.sql.*;
import java.util.ArrayList;
import uts.isd.model.*;

public class DBManager {
    
    private Statement stmt;
    
    public DBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
    }

    // Staff
    // Create
    public static boolean insertStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (email, name, password, phone, city, country, role, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "SELECT email, name, password, phone, city, country, role, department FROM staff";
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
                        rs.getString("department"));
                ListOfStaff.add(staff);
            }
        }
        return ListOfStaff;
    }

    // Get Staff by Email
    public Staff getStaff(String email) throws SQLException {
        String sql = "SELECT email, name, password, phone, city, country, role, department FROM staff WHERE email = ?";
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
                            rs.getString("department"));
                }
            }
        }
        return null;
    }
    
    // Update
    public static boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET name = ?, password = ?, phone = ?, city = ?, country = ?, role = ?, department = ? WHERE email = ?";
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
            boolean rowUpdated = st.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
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
    public ArrayList<Product> displayProducts() throws SQLException {
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

        public static boolean insertProduct(Product p) throws SQLException {
            String sql = "INSERT INTO product (productID, productName, description, price, stock, category, supplier, manuDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = DBConnector.getConnection();
                    PreparedStatement st = connection.prepareStatement(sql)) {
                st.setString(1, p.getProductID());
                st.setString(2, p.getName());
                st.setString(3, p.getDescription());
                st.setFloat(4, p.getPrice());
                st.setInt(5, p.getStockQuantity());
                st.setString(6, p.getSupplier());
                st.setString(7, p.getCategory());
                st.setDate(8, p.getManuDate());
                boolean rowInserted = st.executeUpdate() > 0;
                return rowInserted;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

    //Customer

    //Order
    
    //LineItem

    }
