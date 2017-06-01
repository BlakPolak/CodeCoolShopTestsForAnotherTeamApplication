package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by ppolak on 01.06.17.
 */
class ProductDaoSqliteTest {
    Supplier mockedSupplier;
    ProductCategory mockedProductCategory;
    Connection connection;
    ProductDaoSqlite productDaoSqlite;


    @BeforeEach
    public void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/ProductDaoSqliteTest.sql");
        productDaoSqlite = new ProductDaoSqlite(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void testFindMethodWithNoExistingId(){
        Product product = productDaoSqlite.find(8);
        assertThrows(NullPointerException.class, () -> {product.getId();});
    }

    @Test
    void testRemoveProductWhenNoExist(){
        assertThrows(IllegalArgumentException.class, () -> {productDaoSqlite.remove(8);;});
    }


}