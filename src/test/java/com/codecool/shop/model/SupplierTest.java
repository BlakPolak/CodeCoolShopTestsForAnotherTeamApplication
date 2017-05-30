package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
    private Supplier supplier;

    @BeforeEach
    void executeBeforeEach() {
        supplier = new Supplier("test", "test");
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
}