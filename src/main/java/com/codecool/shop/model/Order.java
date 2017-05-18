package com.codecool.shop.model;


public class Order {
    private Integer id;
    private Integer userId;

    public Order(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
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
}
