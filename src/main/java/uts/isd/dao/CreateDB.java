package uts.isd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// pass = isduts

public class CreateDB {
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String user = "root";
    static final String pass = "isduts";

    public static void main(String[] args){
        try(Connection conn = DriverManager.getConnection(url, user, pass);
        Statement stmt = conn.createStatement();)
        {
            String sql = "CREATE DATABASE isd";
            stmt.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
