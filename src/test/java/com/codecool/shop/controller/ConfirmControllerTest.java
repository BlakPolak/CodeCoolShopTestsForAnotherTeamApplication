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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ConfirmControllerTest {
    Request request;
    Response response;
    Basket basket;
    ConfirmController confirmController;
    Session session;
    Connection connection;

    @BeforeEach
    void setup() {
        request = mock(Request.class, Mockito.RETURNS_DEEP_STUBS);
        response = mock(Response.class, Mockito.RETURNS_DEEP_STUBS);
        basket = mock(Basket.class, Mockito.RETURNS_DEEP_STUBS);
        session = mock(Session.class, Mockito.RETURNS_DEEP_STUBS);
        connection = mock(Connection.class);
        confirmController = new ConfirmController(connection);
    }


}