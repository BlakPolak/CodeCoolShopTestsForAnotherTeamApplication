package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    @Test
    void testSetAndGetName() {
        ProductCategory category = new ProductCategory("dupa", "dupa", "dupa");
        category.setName("NewName");
        assertEquals("NewName", category.getName());
    }

    @Test
    void testSetAndGetDepartment() {
        ProductCategory category = new ProductCategory("dupa", "dupa", "dupa");
        category.setDepartment("NewDepartment");
        assertEquals("NewDepartment", category.getDepartment());
    }

    @Test
    void testSetAndGetDescription() {
        ProductCategory category = new ProductCategory("dupa", "dupa", "dupa");
        category.setDescription("NewDescription");
        assertEquals("NewDescription", category.getDescription());
    }

    @Test
    public void testFailIfAddProductTakeNull() {
        ProductCategory category = new ProductCategory("dupa", "dupa", "dupa");
        assertThrows(NullPointerException.class, () -> {
            category.addProduct(null);
        });
    }
}