package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductCategoryDaoSqliteTest {

    ProductCategoryDaoSqlite productCategoryDaoSqlite;
    ProductCategory productCategory;
    Connection connection;

    @BeforeEach
    void setup() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        productCategoryDaoSqlite = new ProductCategoryDaoSqlite(connection);
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/ProductCategoryDaoSqlite.sql");
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void testIfFindMethodFailWhenIdLTZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            productCategoryDaoSqlite.find(-1);
            });
    }

    @Test
    void testGetAllReturnsExpectedListSize() {
        List<ProductCategory> categories = new ArrayList<>();
        productCategory = new ProductCategory("test", "test", "test");
        categories.add(productCategory);
        assertEquals(categories.size(), productCategoryDaoSqlite.getAll().size());
    }

}