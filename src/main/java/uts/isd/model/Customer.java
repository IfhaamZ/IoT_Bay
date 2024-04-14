package uts.isd.model;

import java.util.*;

public class Customer extends User {
    
    private int customerID;
    private List<Order> orderHistory;
    private String paymentDetails;
    private String shipmentDetails;
    
    public Customer(String _email, String _name, String _password, String _phone, String _city, String _country,
            String _role, int customerID, List<Order> orderHistory, String paymentDetails, String shipmentDetails) {
        super(_email, _name, _password, _phone, _city, _country, _role);
        this.customerID = customerID;
        this.orderHistory = orderHistory;
        this.paymentDetails = paymentDetails;
        this.shipmentDetails = shipmentDetails;
    }

     public List<Product> browseDevices() {
        return new ArrayList<>();
    }

    public Product searchDevice() {
        return null;
    }

    public Order placeOrder(int orderID, String datePlaced, String status, int customerID) {
        Order newOrder = new Order(orderID, datePlaced, status, customerID);
        return newOrder;
    }

    public String trackOrder() {
        String status = "";
        return status;
    }

    public void manageAccount() {
        
    }

    public List<AccessLog> viewHistoryLogs() {
        List<AccessLog> accessLog = new ArrayList<AccessLog>();
        return accessLog;
    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(String shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }
}
