package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import db.TestSqliteJDBCConnector;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.sql.SQLException;

class ProductControllerTest {

    Request request;
    Response response;
    Basket basket;
    BasketController basketController;
    Session session;
    Connection connection;
    ProductController productController;
    ProductDaoSqlite productDao;
    ProductCategoryDao productCategoryDao;
    SupplierDao supplierDao;

    @BeforeEach
    void setup() throws IOException, SQLException {
        request = mock(Request.class);
        response = mock(Response.class);
        connection = mock(Connection.class);
        supplierDao = mock(SupplierDao.class);
        productDao = mock(ProductDaoSqlite.class);
        basketController = new BasketController(connection);
        productCategoryDao = mock(ProductCategoryDao.class);
        basket = mock(Basket.class);
        session = mock(Session.class);
        productController = new ProductController(connection, productDao, productCategoryDao, supplierDao);
    }

    @Test
    void testIfIndexReturnsExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        when(session.attribute("basket")).thenReturn(basket);
        when(productDao.getAll()).thenReturn(Arrays.asList(mock(Product.class), mock(Product.class)));
        when(productCategoryDao.getAll()).thenReturn(Arrays.asList(mock(ProductCategory.class), mock(ProductCategory.class)));
        when(supplierDao.getAll()).thenReturn(Arrays.asList(mock(Supplier.class), mock(Supplier.class)));

        Map<String, Object> params = new HashMap<>();
        params.put("basket", basket);
        params.put("suppliers", Arrays.asList(mock(Supplier.class), mock(Supplier.class)));
        params.put("categories", Arrays.asList(mock(ProductCategory.class), mock(ProductCategory.class)));
        params.put("products", Arrays.asList(mock(Product.class), mock(Product.class)));
        ModelAndView modelAndView = new ModelAndView(params, "product/index");
        assertSame(modelAndView.getViewName(), productController.index(request, response).getViewName());
    }

    @Test
    void testIfIndexFilterReturnsExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        Map<String, Object> params = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(params, "product/index");
        assertSame(modelAndView.getViewName(), productController.indexFilter(request, response).getViewName());
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
