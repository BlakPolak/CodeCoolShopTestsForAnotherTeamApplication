package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    Integer add(Order order);
    Order find(int id);
    void remove(int id);
    List<Order> getAll();
    List<Order> createOrdersList(ResultSet rs) throws SQLException;

    void updatePaid(Integer orderId);
}
