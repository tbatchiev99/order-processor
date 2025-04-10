package com.ibm.processor.orderapp.dto;

public class OrderDto {

    private String orderName;
    private String product;
    private Integer quantity;

    public OrderDto() {

    }

    public OrderDto(String orderName, String product, Integer quantity) {
        this.orderName = orderName;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
