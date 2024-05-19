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

    // Staff Management
    // Create
    public static boolean insertStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (email, name, password, phone, city, country, role, department, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            st.setString(1, staff.getEmail());
            st.setString(2, staff.getName());
            st.setString(3, staff.getPassword());
            st.setString(4, staff.getPhone());
            st.setString(5, staff.getCity());
            st.setString(6, staff.getCountry());
            st.setString(7, staff.getRole());
            st.setString(8, staff.getDepartment());
            st.setBoolean(9, staff.isActive());
            // Execute update and return result
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
            // Iterate through result set and add staff to the list
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
            // Set parameters for the prepared statement
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + role + "%");
            st.setString(3, "%" + department + "%");
            try (ResultSet rs = st.executeQuery()) {
                // Iterate through result set and add staff to the list
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
            // Set parameter for the prepared statement
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                // Check if a result exists and create a staff object
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
            // Set parameters for the prepared statement
            st.setString(1, staff.getName());
            st.setString(2, staff.getPassword());
            st.setString(3, staff.getPhone());
            st.setString(4, staff.getCity());
            st.setString(5, staff.getCountry());
            st.setString(6, staff.getRole());
            st.setString(7, staff.getDepartment());
            st.setBoolean(8, staff.isActive());
            st.setString(9, staff.getEmail());
            // Execute update and return result
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
            // Set parameter for the prepared statement
            st.setString(1, email);
            // Execute update and return result
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Deactivate staff
    public static boolean deactivateStaff(String email) throws SQLException {
        String sql = "UPDATE staff SET status = false WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            // Set parameter for the prepared statement
            st.setString(1, email);
            // Execute update and return result
            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Delete Staff
    public boolean deleteStaff(String email) throws SQLException {
        String sql = "DELETE FROM staff WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            // Set parameter for the prepared statement
            st.setString(1, email);
            // Execute update and return result
            boolean rowDeleted = st.executeUpdate() > 0;
            return rowDeleted;
        }
    }

    // Product
    // Display product
    public ArrayList<Product> displayProducts() throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM product");
        ArrayList<Product> products = new ArrayList<>();
        while (rs.next()) {
            String productID = rs.getString("productID");
            String productName = rs.getString("productName");
            String description = rs.getString("description");
            float price = rs.getFloat("price");
            int stock = rs.getInt("stock");
            String category = rs.getString("category");
            String supplier = rs.getString("supplier");
            Date manuDate = rs.getDate("manuDate");
            products.add(new Product(productID, productName, description, price, stock, category, supplier, manuDate));
        }
        return products;
    }

    // Insert new product
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

    // Delete product
    public boolean deleteProduct(String productID) throws SQLException {
        String sql = "DELETE FROM product WHERE productID = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, productID);
            boolean rowDeleted = st.executeUpdate() > 0;
            return rowDeleted;
        }
    }

    // Get product 
    public Product getProduct(String productID) throws SQLException {
        String sql = "SELECT productID, productName, description, price, stock, category, supplier, manuDate FROM product WHERE productID = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, productID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getString("productID"),
                            rs.getString("productName"),
                            rs.getString("description"),
                            rs.getFloat("price"),
                            rs.getInt("stock"),
                            rs.getString("category"),
                            rs.getString("supplier"),
                            rs.getDate("manuDate"));
                }
            }
        }
        return null;
    }

    // Update
    public static boolean updateProduct(Product p) {
        String sql = "UPDATE product SET productName = ?, description = ?, price = ?, stock = ?, supplier = ?, category = ?, manuDate = ? WHERE productID = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.setFloat(3, p.getPrice());
            st.setInt(4, p.getStockQuantity());
            st.setString(5, p.getSupplier());
            st.setString(6, p.getCategory());
            st.setDate(7, p.getManuDate());
            st.setString(8, p.getProductID());
            boolean rowUpdated = st.executeUpdate() > 0;
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> searchProduct(String name, String category) throws SQLException {
        List<Product> productView = new ArrayList<>();
        String sql = "SELECT productID, productName, description, price, stock, category, supplier, manuDate FROM product WHERE productName LIKE ? AND category LIKE ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            st.setString(2, "%" + category + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product(
                            rs.getString("productID"),
                            rs.getString("productName"),
                            rs.getString("description"),
                            rs.getFloat("price"),
                            rs.getInt("stock"),
                            rs.getString("category"),
                            rs.getString("supplier"),
                            rs.getDate("manuDate"));
                    productView.add(p);
                }
            }
        }
        return productView;
    }

    //User
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
     
    public boolean deleteUser(String email) throws SQLException {
        String sql = "DELETE FROM account where email = ?";

        try (Connection connection = DBConnector.getConnection();
            PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
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

    // Search orders by customerID
    public List<Order> searchOrdersByCustomerID(int customerID) throws SQLException {
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

    //Payment
    public boolean createPayment(Payment payment) throws SQLException {
        String sql = "INSERT INTO payment (paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount, paymentDate) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, payment.getPaymentID());
            statement.setString(2, payment.getMethod());
            statement.setString(3, payment.getCardNum());
            statement.setString(4, payment.getExpMonth());
            statement.setString(5, payment.getExpYear());
            statement.setString(6, payment.getCVN());
            statement.setString(7, payment.getGCNum());
            statement.setString(8, payment.getPIN());
            statement.setString(9, payment.getPaymentAmount());
            statement.setString(10, payment.getPaymentDate());

            boolean rowInserted = statement.executeUpdate() > 0;
            return rowInserted;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Payment> listAllPayments() throws SQLException {
        List<Payment> listPayments = new ArrayList<>();
        String sql = "SELECT * FROM payment";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet resultSet = st.executeQuery()) {
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

                Payment payment = new Payment(paymentID, method, cardNum, expMonth, expYear, cvn, GCNum, pin,
                        paymentAmount,
                        paymentDate);
                listPayments.add(payment);
            }
        }
        return listPayments;
    }

    public boolean deletePayment(Payment payment) throws SQLException {
        String sql = "DELETE FROM payment where paymentID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, payment.getPaymentID());
            boolean rowDeleted = statement.executeUpdate() > 0;
            return rowDeleted;
        }
    }

    public boolean updatePayment(Payment payment) throws SQLException {
        String sql = "UPDATE payment SET method = ?, cardNum = ?, expMonth = ?, expYear = ?, cvn = ?, GCNum = ?, pin = ?, paymentAmount = ?, paymentDate = ?";
        sql += " WHERE paymentID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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
            return rowUpdated;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Payment getPayment(String paymentID) throws SQLException {
        Payment payment = null;
        String sql = "SELECT * FROM payment WHERE paymentID = ?";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {        
                
            statement.setString(1, paymentID);
            try (ResultSet resultSet = statement.executeQuery()) {
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
                    return payment;
                }
            }
        }
        return null;
    }

}


