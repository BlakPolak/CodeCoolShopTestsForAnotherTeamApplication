package com.codecool.shop.dao;

import com.codecool.shop.model.*;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BasketDaoSqliteTest {
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

    @Test
    void testForAddToBasket() {
        mockedProductCategory = mock(ProductCategory.class);
        mockedSupplier = mock(Supplier.class);
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite(connection);
        Product product = new Product(1, "name", (float) 22, "PLN", "description", mockedProductCategory, mockedSupplier);
        productDaoSqlite.insert(product);
        Basket basket = new Basket();
        basket.add(product, 3);
        basketDaoSqlite.add(basket, 12);
        assertEquals(basket, basketDaoSqlite.find(12));
    }

    @Test
    void testIfFindMethodReturnNullWhenGivenIdIsNotInDatabase() throws SQLException {
        assertEquals(null, basketDaoSqlite.find(123));
    }


    @Test
    void testIfFindMethodThrowsRuntimeExceptionWhenGivenIdIsNotInDatabase() throws SQLException {
        assertThrows(RuntimeException.class, () -> {
            basketDaoSqlite.find(123);
        });
    }

    @Test
    void testIfCreateBasketThrowsIllegalArgumentExceptionWithResulSetAsNull() throws SQLException {
        assertThrows(IllegalArgumentException.class, () -> {
            basketDaoSqlite.createBasketList(null);
        });
    }

}