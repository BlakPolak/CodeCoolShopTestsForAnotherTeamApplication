package com.codecool.shop.controller;

import com.codecool.shop.model.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasketControllerTest {

    Request request;
    Response response;
    Basket basket;
    BasketController basketController;
    Session session;
    Connection connection;

    @BeforeEach
    void setup() {
        request = mock(Request.class, Mockito.RETURNS_DEEP_STUBS);
        response = mock(Response.class, Mockito.RETURNS_DEEP_STUBS);
        connection = mock(Connection.class);
        basketController = new BasketController(connection);
        basket = mock(Basket.class);
        session = mock(Session.class);
    }

    @Test
    void testIfRenderBasketReturnsExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        when(session.attribute("basket")).thenReturn(basket);
        Map<String, Object> params = new HashMap<>();
        params.put("basket", basket);
        ModelAndView modelAndView = new ModelAndView(params, "product/basket");
        assertSame(modelAndView, basketController.renderBasket(request, response));
    }





//    @Test
//    void testIfAddTCart () {
//        when(request.session().attribute("basket")).thenReturn(basket);
//        Map<String, Object> params = new HashMap<>();
//        params.put("basket", basket);
//        ModelAndView modelAndView = new ModelAndView(params, "product/basket");
//        basketController.renderBasket(request, response);
//        assertEquals(modelAndView, basketController.renderBasket(request, response));
//    }
}
