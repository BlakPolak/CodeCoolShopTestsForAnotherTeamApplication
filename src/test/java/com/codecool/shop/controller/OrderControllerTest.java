package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    Request request;
    Response response;
    OrderController orderController;
    Session session;
    Connection connection;
    OrderDao orderDao;

    @BeforeEach
    void setup() throws IOException, SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        request = mock(Request.class, Mockito.RETURNS_DEEP_STUBS);
        response = mock(Response.class, Mockito.RETURNS_DEEP_STUBS);
        orderController = new OrderController(connection);
        orderDao = mock(OrderDao.class);
        session = mock(Session.class);
    }

    @Test
    void testIfRenderShowAllExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        when(session.attribute("order")).thenReturn(orderDao);
        Map<String, Object> params = new HashMap<>();
        params.put("order", orderDao);
        ModelAndView modelAndView = new ModelAndView(params, "admin/orderslist");
        assertSame(modelAndView.getViewName(), orderController.showAll(request, response).getViewName());
    }
}