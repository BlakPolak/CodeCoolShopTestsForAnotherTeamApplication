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
}