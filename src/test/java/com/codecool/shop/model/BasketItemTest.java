package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BasketItemTest {
    private ProductCategory category;
    private Supplier supplier;
    private Product product;
    private BasketItem basketItem;

    @BeforeEach
    void setup() {
        this.category = new ProductCategory("","","");
        this.supplier = new Supplier("", "");
        this.product = new Product("", 1.1f, "PLN", "", category, supplier);
        this.basketItem = new BasketItem(product, 1);
    }

    @Test
    public void testSetAndGetProduct() {
        basketItem.setProduct(product);
        assertEquals(product, basketItem.getProduct());
    }

    @Test
    public void testSetAndGetQuantity() {
        basketItem.setQuantity(5);
        assertEquals(5, basketItem.getQuantity(), 0.00000001);
    }

}