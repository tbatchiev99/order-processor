package com.ibm.processor.orderapp.dto;

public class CreateOrderDto {

    private String name;
    private Integer productId;
    private Integer quantity;

    public CreateOrderDto() {

    }

    public CreateOrderDto(String name, Integer productId, Integer quantity) {
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
