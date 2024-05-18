package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Payment;

public class PaymentDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection con;

    // Constructor to initialize PaymentDAO with an existing database connection

    public PaymentDAO(Connection connection) throws SQLException {
        this.con = connection;
        connection.setAutoCommit(true);
    }

    public PaymentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

//establishing a connection to the database
//this method can be called by other methods to simply ensure
//there is an active connection to the database before performing 
//database operations

    protected void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            con = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    //method that closes connection to database

    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }


    //inserts a new payment record into the database
    public boolean CreatePayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO PaymentManagement (method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount, paymentDate) VALUES (?,?,?,?,?,?,?,?,?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, payment.getMethod());
        statement.setString(2, payment.getCardNum());
        statement.setString(3, payment.getExpMonth());
        statement.setString(4, payment.getExpYear());
        statement.setString(5, payment.getCVN());
        statement.setString(6, payment.getGCNum());
        statement.setString(7, payment.getPIN());
        statement.setString(8, payment.getPaymentAmount());
        statement.setString(5, payment.getPaymentDate());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    //method to retrieve all payment records and attributes from database 

    public List<Payment> listAllPayments() throws SQLException {
        List<Payment> listPayments = new ArrayList<>();

        String sql = "SELECT * FROM PaymentManagement";

        connect();

        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String paymentID = resultSet.getString("paymentID");
            String method = resultSet.getString("method");
            String cardNum = resultSet.getString("cardNum");
            String expMonth = resultSet.getString("expMonth");
            String expYear = resultSet.getString("expYear");
            String cvn = resultSet.getString("cvn");
            String GCNum = resultSet.getString("GCNum");
            String pin = resultSet.getString("pin");
            String paymentAmount = resultSet.getString("paymentAmount");
            String paymentDate = resultSet.getString("paymentDate");

            Payment payment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                    paymentDate);
            listPayments.add(payment);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listPayments;
    }

    //Deletes a payment record from database, depending on the paymentID

    public boolean deletePayment(String paymentID) throws SQLException {
        String sql = "DELETE FROM PaymentManagement where paymentID = ?";

        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, paymentID);

        // returns true if row was deleted, returns false otherwise
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }


    //updates an existing payment record in the database, based on the paymentID
    public boolean updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE PaymentManagement SET method = ?, cardNum = ?, expMonth = ?, expYear = ?, cvn = ?, GCNum = ?, pin = ?, paymentAmount = ?, paymentDate = ?";
        sql += " WHERE paymentID = ?";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, payment.getMethod());
        statement.setString(2, payment.getCardNum());
        statement.setString(3, payment.getExpMonth());
        statement.setString(4, payment.getExpYear());
        statement.setString(5, payment.getCVN());
        statement.setString(6, payment.getGCNum());
        statement.setString(7, payment.getPIN());
        statement.setString(8, payment.getPaymentAmount());
        statement.setString(9, payment.getPaymentDate());
        statement.setString(10, payment.getPaymentID());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    //retrieves a single payment record from the database
    //fetches data connected to the specific paymentID
    public Payment getPayment(String paymentID) throws SQLException {
        Payment payment = null;
        String sql = "SELECT * FROM PaymentManagement WHERE paymentID = ?";

        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, paymentID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String method = resultSet.getString("method");
            String cardNum = resultSet.getString("cardNum");
            String expMonth = resultSet.getString("expMonth");
            String expYear = resultSet.getString("expYear");
            String cvn = resultSet.getString("cvn");
            String GCNum = resultSet.getString("GCNum");
            String pin = resultSet.getString("pin");
            String paymentAmount = resultSet.getString("paymentAmount");
            String paymentDate = resultSet.getString("paymentDate");
            payment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount,
                    paymentDate);
        }

        resultSet.close();
        statement.close();

        return payment;
    }
}