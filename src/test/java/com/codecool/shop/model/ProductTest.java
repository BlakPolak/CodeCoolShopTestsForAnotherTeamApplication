package com.codecool.shop.model;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    ProductCategory mockedProductCategory;
    Supplier mockedSupplier;

    @BeforeEach
    public void setUp(){
        mockedProductCategory = mock(ProductCategory.class);
        mockedSupplier = mock(Supplier.class);
    }

    @Test
    void testForSetAndGetCurrency() {
        Product product = new Product("name", (float) 10.0, "PLN", "description", mockedProductCategory, mockedSupplier);
        product.setPrice(5, "USD");
        assertEquals("USD", product.getDefaultCurrency().toString());
    }

    @Test
    void testForSetAndGetPrice() {
        Product product = new Product("name", (float) 10.0, "PLN", "description",mockedProductCategory,mockedSupplier);
        product.setPrice(5, "USD");
        String expected = "5.0 USD";
        assertEquals(expected, product.getPrice());
    }
}