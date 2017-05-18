package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface BasketDao {

    void add(Basket basket, Integer orderId);
    Basket find(int id);
    void remove(int id);
    public Basket createBasketList(ResultSet rs) throws SQLException;
    void getAll();
}
