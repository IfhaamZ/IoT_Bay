package uts.isd.model;

import java.sql.Date;

public class Product {

    private String productID;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private String category;
    private String supplier;
    private Date manuDate;

    public Product(String productID, String name, String description, float price, int stockQuantity, String category,
            String supplier, Date manuDate) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.supplier = supplier;
        this.manuDate = manuDate;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
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

    public String getSupplier() {
        return supplier;
    }

    public String getCategory() {
        return category;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setManuDate(Date manuDate) {
        this.manuDate = manuDate;
    }

    public boolean updateStock() {
        return true;
    }

    public boolean updateDetails() {
        return true;
    }
}