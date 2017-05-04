package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kamil on 02.05.17.
 */
public class SqliteJDBCConnector {


    public static Connection connection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void createTables() throws SQLException{
        Connection connection = connection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"products\"\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR NOT NULL,\n" +
                "    description TEXT,\n" +
                "    price DOUBLE DEFAULT 0.00\n" +
                ");\n" +
                "CREATE UNIQUE INDEX products_id_uindex ON \"products\" (id)");
    }
}
