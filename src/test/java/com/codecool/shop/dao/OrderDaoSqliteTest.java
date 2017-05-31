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

    @Test
    public void testAddFailsWhenProductIsNull() {
        assertThrows(NullPointerException.class, ()-> orderDaoSq.add(null));
    }

    @Test
    public void testUpdatePaidFailsWhenIdLT0() {
        assertThrows(IllegalArgumentException.class, ()-> orderDaoSq.updatePaid(-1));
    }
}