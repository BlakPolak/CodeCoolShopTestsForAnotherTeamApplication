package com.codecool.shop.controller;

import com.codecool.shop.model.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BasketControllerTest {

    Request request;
    Response response;
    Basket basket;
    BasketController basketController;

    @BeforeEach
    void setup() {
        request = mock(Request.class, Mockito.RETURNS_DEEP_STUBS);
        response = mock(Response.class, Mockito.RETURNS_DEEP_STUBS);
        basket = mock(Basket.class, Mockito.RETURNS_DEEP_STUBS);
    }


    @Test
    void testIfRenderBasketReturnsExpectedModelAndView () {
        when(request.session().attribute("basket")).thenReturn(basket);
        Map<String, Object> params = new HashMap<>();
        params.put("basket", basket);
        ModelAndView modelAndView = new ModelAndView(params, "product/basket");
        basketController.renderBasket(request, response);
        assertEquals(modelAndView, basketController.renderBasket(request, response));
    }
}
