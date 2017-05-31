package com.codecool.shop.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderDaoSqliteTest {
    private OrderDaoSqlite orderDaoSq;
    Connection connection;

    @BeforeEach
    public void setup() {
        this.orderDaoSq = new OrderDaoSqlite(connection);
    }
}