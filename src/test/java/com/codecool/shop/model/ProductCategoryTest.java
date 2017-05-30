package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    private ProductCategory category;
    private Product product;
    private Supplier supplier;
    private ArrayList<Product> products;

    @BeforeEach
    void executeBeforeEach() {
        supplier = new Supplier("", "");
        category = new ProductCategory("test", "test", "test");
        product = new Product("test", 1.000f, "PLN", "test", category, supplier);
        products = new ArrayList<>();
    }

    @Test
    void testSetAndGetName() {
        category.setName("NewName");
        assertEquals("NewName", category.getName());
    }

    @Test
    void testSetAndGetDepartment() {
        category.setDepartment("NewDepartment");
        assertEquals("NewDepartment", category.getDepartment());
    }

    @Test
    void testSetAndGetDescription() {
        category.setDescription("NewDescription");
        assertEquals("NewDescription", category.getDescription());
    }

    @Test
    void testSetAndGetProducts() {
        products.add(product);
        category.setProducts(products);
        assertEquals(products, category.getProducts());
    }

    @Test
    void testFailIfAddProductTakeNull() {
        assertThrows(NullPointerException.class, () -> {
            category.addProduct(null);
        });
    }
}