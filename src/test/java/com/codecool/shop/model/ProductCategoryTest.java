package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    private ProductCategory category;

    @BeforeEach
    void executeBeforeEach() {
        category = new ProductCategory("test", "test", "test");
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
    void testFailIfAddProductTakeNull() {
        assertThrows(NullPointerException.class, () -> {
            category.addProduct(null);
        });
    }
}