package com.ibm.processor.orderapp.dto;

import java.time.Instant;

public class OrderDto {

    private Integer id;
    private String name;
    private String productName;
    private Integer quantity;
    private String orderNr;
    private Instant orderedOn;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderNr() {
        return orderNr;
    }

    public void setOrderNr(String orderNr) {
        this.orderNr = orderNr;
    }

    public Instant getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Instant orderedOn) {
        this.orderedOn = orderedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
