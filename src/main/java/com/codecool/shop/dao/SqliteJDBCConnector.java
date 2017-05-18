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
//<<<<<<< HEAD
//        statement.execute("CREATE TABLE IF NOT EXISTS \"products\"\n" +
//
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "name VARCHAR(100)," +
//                "description TEXT," +
//                "price DOUBLE DEFAULT 0.00," +
//                "category_id INTEGER," +
//                "supplier_id INTEGER," +
//                "CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)," +
//                "CONSTRAINT products_suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (id))");
//       statement.execute("CREATE TABLE IF NOT EXISTS categories" +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "name VARCHAR(255) NOT NULL," +
//                "description TEXT NOT NULL," +
//                "department TEXT NOT NULL)" );
//        statement.execute("CREATE TABLE suppliers\n" +
//                "(\n" +
//                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                "    name VARCHAR(255) NOT NULL,\n" +
//                "    description TEXT NOT NULL\n" +
//                ")");

//        products table
        statement.execute("CREATE TABLE IF NOT EXISTS \"products\"\n" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(100)," +
                "description TEXT," +
                "price DOUBLE DEFAULT 0.00," +
                "category_id INTEGER," +
                "supplier_id INTEGER," +
                "CONSTRAINT products_categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)," +
                "CONSTRAINT products_suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (id))");
//        statement.execute("CREATE TABLE IF NOT EXISTS categories" +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "name VARCHAR(255) NOT NULL," +
//                "description TEXT NOT NULL," +
//                "department TEXT NOT NULL)" );
//        statement.execute("CREATE TABLE IF NOT EXISTS suppliers\n" +
//                "(\n" +
//                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                "    name VARCHAR(255) NOT NULL,\n" +
//                "    description TEXT NOT NULL\n" +
//                ")");
        statement.execute("CREATE TABLE IF NOT EXISTS users\n" +
                "(\n" +
                "    id INT PRIMARY KEY NOT NULL,\n" +
                "    first_name VARCHAR(50) NOT NULL,\n" +
                "    last_name VARCHAR(100),\n" +
                "    adres TEXT NOT NULL,\n" +
                "    phone TEXT NOT NULL,\n" +
                "    email TEXT\n" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS orders\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    user_id INTEGER NOT NULL,\n" +
                "    paid BOOLEAN NOT NULL,\n" +
                "    send BOOLEAN NOT NULL\n" +
                ")");
        statement.execute("CREATE TABLE IF NOT EXISTS baskets\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    order_id INTEGER NOT NULL,\n" +
                "    product_id INTEGER NOT NULL,\n" +
                "    quantity INTEGER NOT NULL\n" +
                ");");

//        categories table
        statement.execute("CREATE TABLE IF NOT EXISTS categories\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR(255) NOT NULL\n" +
                ", department TEXT NULL, description TEXT NULL);\n" +
                "CREATE UNIQUE INDEX categories_id_uindex ON categories (id)");
//        supplier table
        statement.execute("CREATE TABLE IF NOT EXISTS suppliers\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name VARCHAR(255) NOT NULL\n" +
                ", description TEXT NULL)");

    }
}
