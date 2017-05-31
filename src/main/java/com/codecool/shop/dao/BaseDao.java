package com.codecool.shop.dao;


import java.sql.Connection;

class BaseDao {
    private Connection connection;

    BaseDao(Connection connection){
        this.connection = connection;
    }

    Connection getConnection() {
        return this.connection;
    }
}

