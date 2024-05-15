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

public class UserDAO {
    private String db_url;
    private String db_user;
    private String db_pass;
    private Connection jdbcConnection;
     
    public UserDAO(String db_url, String db_user, String db_pass) {
        this.db_url = "jdbc:mysql://localhost:3306/Database";
        this.db_user = "root";
        this.db_pass = "Password";
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                db_url, db_user, db_pass);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO account (name, password, email) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();

        System.out.println("Create User ID: " + user.getId() + " - Deleted: " + rowInserted);

        return rowInserted;

    }
     
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<>();
         
        String sql = "SELECT * FROM account";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("AccountID");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
             
            User user = new User(id, name, password, email);
            listUser.add(user);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listUser;
    }
     
    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM account where AccountID = ?";
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, user.getId());
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        
        System.out.println("Delete User ID: " + user.getId() + " - Deleted: " + rowDeleted); // Add this line for logging
        
        return rowDeleted;
    }
     
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE account SET name = ?, password = ?, email = ?";
        sql += " WHERE AccountID = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setInt(4, user.getId());
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM account WHERE AccountID = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
             
            user = new User(id, name, password, email);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }


}
