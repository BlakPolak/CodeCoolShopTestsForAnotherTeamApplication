package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
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
        supplier.setName("NewName");
        assertEquals("NewName", supplier.getName());
    }

    @Test
    void testSetAndGetDescription() {
        supplier.setDescription("NewDescription");
        assertEquals("NewDescription", supplier.getDescription());
    }

    @Test
    void testSetAndGetProducts() {
        products.add(product);
        supplier.setProducts(products);
        assertEquals(products, supplier.getProducts());
    }

    @Test
    void testFailIfAddProductTakeNull() {
        assertThrows(NullPointerException.class, () -> {
            supplier.addProduct(null);
        });
    }
}