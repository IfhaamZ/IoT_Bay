
package uts.isd.model;

public class LineItem {

    private int lineItemID;
    private int orderID;
    private int productID;
    private int quantity;
    private float lineItemTotal;
  
    public LineItem(int lineItemID, int orderID, int productID, int quantity) {
      this.lineItemID = lineItemID;
      this.orderID = orderID;
      this.productID = productID;
      this.quantity = quantity;
      this.lineItemTotal = calculateTotal(); 
    }
  
    public int getLineItemID() {
      return lineItemID;
    }
  
    public int getOrderID() {
      return orderID;
    }
  
    public int getProductID() {
      return productID;
    }
  
    public int getQuantity() {
      return quantity;
    }
  
    public float getLineItemTotal() {
      return lineItemTotal;
    }
  
    private float calculateTotal() {
      return quantity;
    }

    public int getProductQuantity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductQuantity'");
    }

    public float getLineItemPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLineItemPrice'");
    }

    public String getProductSKU() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductSKU'");
    }

    public String getLineItemStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLineItemStatus'");
    }
  }