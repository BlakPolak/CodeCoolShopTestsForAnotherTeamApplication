package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class OrderTest {
    private Basket basket;
    private User user;
    private Order order;

    @BeforeEach
    void setUp() {
        this.order = new Order(12);
        this.basket = mock(Basket.class);
        this.user = mock(User.class);
    }

    @Test
    void testGetAndSetBasket() {
        this.order.setBasket(this.basket);
        assertEquals(this.order.getBasket(), this.basket);
    }
}