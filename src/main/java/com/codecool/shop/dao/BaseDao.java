package com.codecool.shop.dao;

import com.codecool.shop.Application;

import java.sql.Connection;

class BaseDao {

    Connection getConnection() {

        return Application.getApp().getConnection();
    }
}

