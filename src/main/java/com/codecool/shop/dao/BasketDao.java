package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BasketDao {

    void add(Basket basket, Integer orderId);
    Basket find(int id);
    Basket createBasketList(ResultSet rs) throws SQLException;
}
