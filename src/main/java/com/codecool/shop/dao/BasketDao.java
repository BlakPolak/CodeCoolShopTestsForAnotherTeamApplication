package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;

import java.util.List;

public interface BasketDao {

    void add(Basket basket);
    Basket find(int id);
    void remove(int id);

    List<Basket> getAll();
}
