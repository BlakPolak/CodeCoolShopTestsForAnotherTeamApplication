package com.codecool.shop.model;


public class Order {
    private Integer id;
    private Integer userId;
    private Boolean paid;
    private Boolean send;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.paid = false;
        this.send = false;

    }

    public Order(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }
}
