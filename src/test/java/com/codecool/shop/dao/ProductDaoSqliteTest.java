package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ppolak on 01.06.17.
 */
class ProductDaoSqliteTest {
    Supplier mockedSupplier;
    ProductCategory mockedProductCategory;
    Connection connection;
    BasketDaoSqlite basketDaoSqlite;


    @BeforeEach
    public void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BasketDaoSqliteTest.sql");
        basketDaoSqlite = new BasketDaoSqlite(connection);

    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }
}