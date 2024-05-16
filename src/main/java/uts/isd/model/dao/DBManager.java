package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.User;

public class DBManager {

    private Statement stmt;

    public DBManager(Connection conn) throws SQLException {
        stmt = conn.createStatement();
    }
     
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO account (name, password, email) VALUES (?, ?, ?)";
         
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
                    st.setString(1, user.getName());
                    st.setString(2, user.getPassword());
                    st.setString(3, user.getEmail());
            
            boolean rowInserted = st.executeUpdate() > 0;
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM account";
         
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("AccountID");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Boolean status = rs.getBoolean("status");
                
                User user = new User(id, name, password, email, status);
                listUser.add(user);
            }
        } 
        return listUser;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM account where AccountID = ?";

        try (Connection connection = DBConnector.getConnection();
            PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, user.getId());
            
            boolean rowDeleted = st.executeUpdate() > 0;
    
            return rowDeleted;
        }
    }
     
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE account SET name = ?, password = ?, email = ? WHERE AccountID = ?";
         
            try (Connection conn = DBConnector.getConnection();
                    PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, user.getName());
                    st.setString(2, user.getPassword());
                    st.setString(3, user.getEmail());
                    st.setInt(4, user.getId());
            
            boolean rowUpdated = st.executeUpdate() > 0;

            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     
    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM account WHERE AccountID = ?";
         
         
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id);
         
        ResultSet resultSet = st.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
             
            user = new User(id, name, password, email);
        }
    }
         
        return user;
    }
    
    public List<User> searchUser(String name, String phone) throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE name LIKE ? AND phone LIKE ?";
    
        try (Connection connection = DBConnector.getConnection();
            PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + (name == null ? "" : name) + "%");
            st.setString(2, "%" + (phone == null ? "" : phone) + "%");

            ResultSet resultSet = st.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("AccountID");
            String userName = resultSet.getString("name");
            String userPassword = resultSet.getString("password");
            String userEmail = resultSet.getString("email");
            String userPhone = resultSet.getString("phone");
    
            User user = new User(id, userName, userPassword, userEmail);
            user.setPhone(userPhone);
            userList.add(user);
        }
    }
        return userList;
    }

        
    public boolean activateUser(int id) throws SQLException {
        String sql = "UPDATE account SET status = true WHERE AccountID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsUpdated = st.executeUpdate();
            
            st.close();
            return rowsUpdated > 0;
        }
    }
    
    public boolean deactivateUser(int id) throws SQLException {
        String sql = "UPDATE account SET status = false WHERE AccountID = ?";
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id);
        int rowsUpdated = st.executeUpdate();
        
        st.close();
        return rowsUpdated > 0;
        }
    }
}