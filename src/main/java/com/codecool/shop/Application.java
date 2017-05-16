package com.codecool.shop;


import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.Connection;

public class Application {

    private static Application app = new Application();
    private Connection connection = SqliteJDBCConnector.connection();


    private Application() { }

    public static Application getApp() {
        return app;
    }

    public Connection getConnection() {
        return connection;
    }
}
