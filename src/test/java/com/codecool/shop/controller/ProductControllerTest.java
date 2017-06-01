package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
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

class ProductControllerTest {

    Request request;
    Response response;
    ProductController productController;
    Session session;
    Connection connection;
    ProductDao productDao;
    Basket basket;

    @BeforeEach
    void setup() throws IOException, SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/ProductControllerTest.sql");
        request = mock(Request.class, Mockito.RETURNS_DEEP_STUBS);
        response = mock(Response.class, Mockito.RETURNS_DEEP_STUBS);
        productController = new ProductController(connection);
        productDao = mock(ProductDao.class);
        basket = mock(Basket.class);
        session = mock(Session.class);
    }

    @Test
    void testIfRenderShowProductShowAllExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        when(session.attribute("basket")).thenReturn(basket);
        when(request.params("id")).thenReturn("1");
        Product product = productDao.find(1);
        Map<String, Object> params = new HashMap<>();
        params.put("product", product);
        params.put("basket", basket);
        ModelAndView modelAndView = new ModelAndView(params, "product/product");
        assertSame(modelAndView.getViewName(), productController.showProduct(request, response).getViewName());
    }

}