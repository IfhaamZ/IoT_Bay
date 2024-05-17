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

    // Order
    // Create
    public static boolean insertOrder(Order order) throws SQLException {
        String sql = "INSERT INTO `order` (datePlaced, status, customerID, shippingAddress, billingAddress, createdBy, createdDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, order.getDatePlaced());
            st.setString(2, order.getStatus());
            st.setInt(3, order.getCustomerID());
            st.setString(4, order.getShippingAddress());
            st.setString(5, order.getBillingAddress());
            st.setString(6, order.getCreatedBy());
            st.setString(7, order.getCreatedDate());
            boolean rowInserted = st.executeUpdate() > 0;
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public ArrayList<Order> fetchOrders() throws SQLException {
        ArrayList<Order> ListOfOrders = new ArrayList<>();
        String sql = "SELECT * FROM `order`";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("orderID"),
                        rs.getString("datePlaced"),
                        rs.getString("status"),
                        rs.getInt("customerID"),
                        rs.getString("shippingAddress"),
                        rs.getString("billingAddress"),
                        rs.getString("createdBy"),
                        rs.getString("createdDate"));
                ListOfOrders.add(order);
            }
        }
        return ListOfOrders;
    }

    // Search orders by customerID
    public List<Order> getOrdersByCustomerID(int customerID) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE customerID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, customerID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("orderID"),
                            rs.getString("datePlaced"),
                            rs.getString("status"),
                            rs.getInt("customerID"),
                            rs.getString("shippingAddress"),
                            rs.getString("billingAddress"),
                            rs.getString("createdBy"),
                            rs.getString("createdDate"));
                    orderList.add(order);
                }
            }
        }
        return orderList;
    }

    // Get Order by orderID
    public Order getOrder(int orderID) throws SQLException {
        String sql = "SELECT * FROM `order` WHERE orderID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt("orderID"),
                            rs.getString("datePlaced"),
                            rs.getString("status"),
                            rs.getInt("customerID"),
                            rs.getString("shippingAddress"),
                            rs.getString("billingAddress"),
                            rs.getString("createdBy"),
                            rs.getString("createdDate"));
                }
            }
        }
        return null;
    }

    // Update
    public static boolean updateOrder(Order order) {
        String sql = "UPDATE `order` SET datePlaced = ?, status = ?, customerID = ?, shippingAddress = ?, billingAddress = ?, createdBy = ?, createdDate = ? WHERE orderID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, order.getDatePlaced());
            st.setString(2, order.getStatus());
            st.setInt(3, order.getCustomerID());
            st.setString(4, order.getShippingAddress());
            st.setString(5, order.getBillingAddress());
            st.setString(6, order.getCreatedBy());
            st.setString(7, order.getCreatedDate());
            st.setInt(8, order.getOrderID());
            boolean rowUpdated = st.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteOrder(int orderID) throws SQLException {
        String sql = "DELETE FROM `order` WHERE orderID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderID);
            boolean rowDeleted = st.executeUpdate() > 0;
            return rowDeleted;
        }
    }
}
