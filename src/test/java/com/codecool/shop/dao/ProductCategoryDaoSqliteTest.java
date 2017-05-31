package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductCategoryDaoSqliteTest {

    ProductCategoryDaoSqlite productCategoryDaoSqlite;
    ProductCategory productCategory;
    Connection connection;

    @BeforeEach
    void setup() {
        ProductCategoryDaoSqlite productCategoryDaoSqlite = new ProductCategoryDaoSqlite(connection);
        this.productCategory = mock(ProductCategory.class);
    }
}