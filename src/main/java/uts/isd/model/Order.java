package uts.isd.model;

import java.util.ArrayList;

public class Order {
  private int orderID;
  private String datePlaced;
  private String status;
  private int customerID;
  private String shippingAddress;
  private String billingAddress;
  private String createdBy;
  private String createdDate;
  private ArrayList<LineItem> lineItems;

  // Constructor with essential fields
  public Order(int orderID, String datePlaced, String status, int customerID) {
    this.orderID = orderID;
    this.datePlaced = datePlaced;
    this.status = status;
    this.customerID = customerID;
    this.lineItems = new ArrayList<>();
  }

  public Order(int orderID, String datePlaced, String status, int customerID, String shippingAddress,
      String billingAddress, String createdBy, String createdDate) {
    this(orderID, datePlaced, status, customerID);
    this.shippingAddress = shippingAddress;
    this.billingAddress = billingAddress;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
  }

  // Add a line item to the order
  public boolean addLineItem(LineItem lineItem) {
    return lineItems.add(lineItem);
  }

  // Remove a line item from the order
  public boolean removeLineItem(LineItem lineItem) {
    return lineItems.remove(lineItem);
  }

  // Update the status of the order
  public boolean updateStatus(String newStatus) {
    this.status = newStatus;
    return true;
  }

  // Calculate the total price of all line items in the order
  public float calculateTotal() {
    float total = 0.0f;
    for (LineItem item : lineItems) {
      total += item.getLineItemPrice();
    }
    return total;
  }

  // Getters and setters
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
    return shippingAddress;
  }

  public void setShippingAddress(String shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public String getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(String billingAddress) {
    this.billingAddress = billingAddress;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public ArrayList<LineItem> getLineItems() {
    return lineItems;
  }

  public void setLineItems(ArrayList<LineItem> lineItems) {
    this.lineItems = lineItems;
  }
}
