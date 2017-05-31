package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SqliteJDBCConnector {

    private static Connection connection;

    public static void setConnection(String path) throws SQLException {
        connection = DriverManager.getConnection(path);
    }

    public static Connection getConnection() {
        return connection;
    }
    public static void createTables() throws SQLException{

        Statement statement = connection.createStatement();

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

        statement.execute("CREATE TABLE IF NOT EXISTS users\n" +
                "(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
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

        statement.execute("INSERT INTO users (first_name, last_name, adres, phone, email) VALUES ('Michael', 'Osak', 'olkusz', '12345', 'osakmichal@gmail.com');");
        statement.execute("INSERT INTO suppliers (name, description) VALUES ('audi', 'nice cars');");
        statement.execute("INSERT INTO suppliers (name, description) VALUES ('apple', 'nice phones');");
        statement.execute("INSERT INTO categories (name, description) VALUES ('cars', 'nice cars cat');");
        statement.execute("INSERT INTO categories (name, description) VALUES ('phones', 'nice phones cat');");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('iphone', 'nice phone', 123, 1, 1);");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('samsung', 'nice phone', 103, 1, 1);");
        statement.execute("INSERT INTO products (name, description, price, category_id, supplier_id) VALUES ('audi', 'nice car', 10300, 2, 2);");
        statement.execute("INSERT INTO baskets (order_id, product_id, quantity) VALUES (1, 1, 1);");
        statement.execute("INSERT INTO baskets (order_id, product_id, quantity) VALUES (1, 2, 1);");
        statement.execute("INSERT INTO orders (user_id, paid, send) VALUES (1, 1, 1);");
        connection.close();
    }

    public static void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE products");
        statement.execute("DROP TABLE suppliers");
        statement.execute("DROP TABLE categories");
        statement.execute("DROP TABLE baskets");
        statement.execute("DROP TABLE users");
        statement.execute("DROP TABLE orders");
        createTables();
    }
}