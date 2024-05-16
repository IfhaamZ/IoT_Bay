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
    // private PreparedStatement readSt;
    // private String readQuery = "SELECT Method, Card_Number, Exp_Month, Exp_Year,
    // CVN, GiftCard_Number, PIN, Amount, Date FROM PaymentManagement";

    public PaymentDAO(Connection connection) throws SQLException {
        this.con = connection;
        connection.setAutoCommit(true);
        // readSt = connection.prepareStatement(readQuery);
    }

    public PaymentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

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

    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

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

    public List<Payment> listAllPayments() throws SQLException {
        List<Payment> listPayments = new ArrayList<>();

        String sql = "SELECT * FROM book";

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

    public boolean deletePayment(String paymentID) throws SQLException {
        String sql = "DELETE FROM PaymentManagement where paymentID = ?";

        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, paymentID);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

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

    // public void CreatePayment(String method, String cardNum, String expMonth,
    // String expYear, String cvn, String GCNum,
    // String pin, String paymentAmount, String paymentDate) throws SQLException {
    // PreparedStatement st = con
    // .prepareStatement(
    // "INSERT INTO PaymentManagement(Method, Card_Number, Exp_Month, Exp_Year, CVN,
    // GiftCard_Number, PIN, Amount, Date) VALUES (?,?,?,?,?,?,?,?,?)");
    // st.setString(1, method);
    // st.setString(2, cardNum);
    // st.setString(3, expMonth);
    // st.setString(4, expYear);
    // st.setString(5, cvn);
    // st.setString(6, GCNum);
    // st.setString(7, pin);
    // st.setString(8, paymentAmount);
    // st.setString(9, paymentDate);
    // st.executeUpdate();
    // st.close();
    // }

    // public boolean deletePayment(String paymentDate) throws SQLException {
    // PreparedStatement dst = con.prepareStatement("DELETE FROM PaymentManagement
    // WHERE Date = ?");
    // dst.setString(1, paymentDate);
    // int rowsAffected = dst.executeUpdate();
    // dst.close();
    // return rowsAffected > 0;
    // }

    // public ArrayList<Payment> fetchPayments() throws SQLException {
    // ResultSet rs = readSt.executeQuery();

    // ArrayList<Payment> payments = new ArrayList<Payment>();
    // while (rs.next()) {
    // String method = rs.getString(1);
    // String cardNum = rs.getString(2);
    // String expMonth = rs.getString(3);
    // String expYear = rs.getString(4);
    // String cvn = rs.getString(5);
    // String GCNum = rs.getString(6);
    // String pin = rs.getString(7);
    // String paymentAmount = rs.getString(8);
    // String paymentDate = rs.getString(9);

    // Payment p = new Payment();
    // p.setMethod(method);
    // p.setCardNum(cardNum);
    // p.setExpMonth(expMonth);
    // p.setExpYear(expYear);
    // p.setCVN(cvn);
    // p.setGCNum(GCNum);
    // p.setPIN(pin);
    // p.setPaymentAmount(paymentAmount);
    // p.setPaymentDate(paymentDate);

    // System.out.println(p.getCardNum());

    // payments.add(p);
    // }

    // return payments;
    // }

}