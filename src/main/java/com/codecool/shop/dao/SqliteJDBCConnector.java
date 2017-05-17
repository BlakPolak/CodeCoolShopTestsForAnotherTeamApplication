package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kamil on 02.05.17.
 */
public class SqliteJDBCConnector {


    public static Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }


    public static void createTables() throws SQLException{
        Connection connection = connection();
        Statement statement = connection.createStatement();
//        products table
        statement.execute("CREATE TABLE \"products\"\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR NOT NULL,\n" +
                "    description TEXT,\n" +
                "    price DOUBLE DEFAULT 0.00,\n" +
                "    category_id INT,\n" +
                "    supplier_id INT,\n" +
                "    CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id),\n" +
                "    CONSTRAINT products_supplier_id_fk FOREIGN KEY (supplier_id) REFERENCES suppplier (id)\n" +
                ");\n" +
                "CREATE UNIQUE INDEX products_id_uindex ON \"products\" (id))");
//        categories table
        statement.execute("CREATE TABLE IF NOT EXISTS categories\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR(255) NOT NULL\n" +
                ", department TEXT NULL, description TEXT NULL);\n" +
                "CREATE UNIQUE INDEX categories_id_uindex ON categories (id)");
//        supplier table
        statement.execute("CREATE TABLE suppplier\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR(255) NOT NULL\n" +
                ", description TEXT NULL)");
    }
}
