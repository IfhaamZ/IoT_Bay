package uts.isd.model;

import java.util.ArrayList;

public class Order {
    

    private int orderID;
    private String datePlaced;
    private String status;
    private int customerID;
  
    public Order(int orderID, String datePlaced, String status, int customerID) {
      this.orderID = orderID;
      this.datePlaced = datePlaced;
      this.status = status;
      this.customerID = customerID;
    }
  
    public boolean addLineItem(LineItem lineItem) {
      return true;
    }
  
    public boolean removeLineItem(LineItem lineItem) {
      return true;
    }
  
    public boolean updateStatus(String newStatus) {
      this.status = newStatus;
      return true;
    }
  
    public float calculateTotal() {
      return 0.0f;
    }
  
    public int getOrderID() {
      return orderID;
    }
  
    public void setOrderID(int orderID) {
      this.orderID = orderID;
    }
  
    public String getDatePlaced() {
      return datePlaced;
    }
  
    public void setDatePlaced(String datePlaced) {
      this.datePlaced = datePlaced;
    }
  
    public String getStatus() {
      return status;
    }
  
    public void setStatus(String status) {
      this.status = status;
    }
  
    public int getCustomerID() {
      return customerID;
    }
  
    public void setCustomerID(int customerID) {
      this.customerID = customerID;
    }

    public String getShippingAddress() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getShippingAddress'");
    }

    public String getCreatedBy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatedBy'");
    }

    public String getCreatedDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCreatedDate'");
    }

    public String getBillingAddress() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBillingAddress'");
    }

    public LineItem[] getLineItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLineItems'");
    }

    public void setShippingAddress(String shippingAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setShippingAddress'");
    }

    public void setBillingAddress(String billingAddress) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setBillingAddress'");
    }

    public void setCreatedBy(String createdBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreatedBy'");
    }

    public void setCreatedDate(String createdDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreatedDate'");
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLineItems'");
    }
  }