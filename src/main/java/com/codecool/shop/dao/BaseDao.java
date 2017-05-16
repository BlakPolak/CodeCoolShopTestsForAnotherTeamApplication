package com.codecool.shop.dao;

import com.codecool.shop.Application;

import java.sql.Connection;

public class BaseDao {

    private Connection connection;

//    public BaseDao() {
//        Connection connection = SqliteJDBCConnector.connection();
//        this.setConnection(connection);
//    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public Connection getConnection() {

        return Application.getApp().getConnection();
    }
}

