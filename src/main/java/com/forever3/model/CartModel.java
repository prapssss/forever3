package com.forever3.model;

public class CartModel {
    private int itemId;
    private String itemName;
    private double itemPrice;
    private int quantity;
    private String imageUrl;

    // ✅ Add this no-arg constructor
    public CartModel() {
    }

    public CartModel(int itemId, String itemName, double itemPrice, int quantity, String imageUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Getters and setters

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
