package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kamil on 02.05.17.
 */
public class SqliteJDBVConnector {


    public static Connection connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void createTables(){
        Connection connection = SqliteJDBVConnector.connection();

    }
}
