package uts.isd.model;

public class Product {

    private int productID;
    private String name;
    private String paymentDetails;
    private String description;
    private float price;
    private int stockQuantity;
  
    public Product(int productID, String name, String paymentDetails, String description, float price, int stockQuantity) {
      this.productID = productID;
      this.name = name;
      this.paymentDetails = paymentDetails;
      this.description = description;
      this.price = price;
      this.stockQuantity = stockQuantity;
    }
  
    public int getProductID() {
      return productID;
    }
  
    public String getName() {
      return name;
    }
  
    public String getPaymentDetails() {
      return paymentDetails;
    }
  
    public String getDescription() {
      return description;
    }
  
    public float getPrice() {
      return price;
    }
  
    public int getStockQuantity() {
      return stockQuantity;
    }
  
    public void setProductID(int productID) {
      this.productID = productID;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public void setPaymentDetails(String paymentDetails) {
      this.paymentDetails = paymentDetails;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }
  
    public void setPrice(float price) {
      this.price = price;
    }
  
    public void setStockQuantity(int stockQuantity) {
      this.stockQuantity = stockQuantity;
    }
  
    public boolean updateStock() {
      return true;
    }
  
    public boolean updateDetails() {
      return true;
    }
  }