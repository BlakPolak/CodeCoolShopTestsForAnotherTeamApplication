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

    @Test
    void testGetAndSetUser() {
        this.order.setUser(this.user);
        assertEquals(this.order.getUser(), this.user);
    }

    @Test
    void testGetAndSetId() {
        this.order.setId(1);
        assertEquals((int) this.order.getId(), 1);
    }

    @Test
    void testGetAndSetPaid() {
        this.order.setPaid(true);
        assertEquals(this.order.getPaid(), true);
    }
}