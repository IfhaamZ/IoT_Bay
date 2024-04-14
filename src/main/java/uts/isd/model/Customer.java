package uts.isd.model;

import java.util.*;

public class Customer {
    private int customerID;
    private List<Order> orderHistory;
    private String paymentDetails;
    private String shipmentDetails;

    public Customer(int customerID, List<Order> orderHistory, String paymentDetails, String shipmentDetails) {
        this.customerID = customerID;
        this.orderHistory = orderHistory;
        this.paymentDetails = paymentDetails;
        this.shipmentDetails = shipmentDetails;
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

}