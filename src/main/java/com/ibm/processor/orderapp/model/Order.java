package com.ibm.processor.orderapp.model;

public class Order {

    private String orderName;
    private String product;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getProduct() {
        return product;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
