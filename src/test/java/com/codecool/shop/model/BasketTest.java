package com.codecool.shop.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BasketTest {
    private Basket basket;
    private ProductCategory category;
    private Supplier supplier;
    private Product product;
    private List<BasketItem> items;
    private BasketItem item;

    @BeforeEach
    void setup() {
        this.basket = new Basket();
        this.category = new ProductCategory("","","");
        this.supplier = new Supplier("", "");
        this.product = new Product("", 1.000000001f, "", "", category, supplier);
        this.items = new ArrayList<>();
        this.item = new BasketItem(product, 1);
    }

    @Test
    public void testSetAndGetItems() {
        items.add(item);
        basket.setItems(items);
        assertEquals(items, basket.getItems());
    }

    @Test
    public void testAddToBasketFailsWhenQuantityLT1() {
        assertThrows(IllegalArgumentException.class, ()-> basket.add(product, -1));
    }

    @Test
    public void testAddFailsIfProductIsNull() {
        assertThrows(NullPointerException.class, ()-> basket.add(null, 1));
    }
}