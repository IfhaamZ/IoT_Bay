package uts.isd.model;

public class Order {
    
}

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
  }