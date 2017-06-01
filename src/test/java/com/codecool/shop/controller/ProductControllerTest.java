package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
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
    ProductCategoryDao productCategoryDao;
    SupplierDaoSqlite supplierDao;


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
    void testIfRenderAdminProductEditShowExpectedView () {
        SupplierDaoSqlite supplierDao = new SupplierDaoSqlite(connection);
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite(connection);
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite(connection);
        when(request.queryParams("name")).thenReturn("name");
        when(request.queryParams("price")).thenReturn("20");
        when(request.queryParams("supplier")).thenReturn("1");
        when(request.queryParams("category")).thenReturn("1");
        when(request.params(":id")).thenReturn("1");
        String name = request.queryParams("name");
        String price = request.queryParams("price");
        String description = request.queryParams("name");
        String id = request.params(":id");
        String currency = "PLN";
        Supplier supplier = supplierDao.find(Integer.parseInt(id));
        ProductCategory productCategory = productCategoryDao.find(Integer.parseInt(id));
        Product product = new Product(name, Float.parseFloat(price), currency, description, productCategory, supplier);
        productDaoSqlite.update(product);
        Map<String, Object> params = new HashMap<>();
        params.put("product", productDao.find(Integer.parseInt(id)));
        params.put("suppliers", supplierDao.getAll());
        params.put("categories", productCategoryDao.getAll());
        ModelAndView modelAndView = new ModelAndView(params, "admin/productEdit");
        assertSame(modelAndView.getViewName(), productController.adminProductEdit(request, response).getViewName());
    }

    @Test
    void testIfRenderAdminProductInsertShowExpectedView () {
        SupplierDaoSqlite supplierDao = new SupplierDaoSqlite(connection);
        ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite(connection);
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite(connection);
        when(request.queryParams("name")).thenReturn("name");
        when(request.queryParams("price")).thenReturn("20");
        when(request.queryParams("supplier")).thenReturn("1");
        when(request.queryParams("category")).thenReturn("1");
        when(request.params("id")).thenReturn("1");
        String name = request.queryParams("name");
        String price = request.queryParams("price");
        String description = request.queryParams("name");
        String id = request.params("id");
        String currency = "PLN";
        Supplier supplier = supplierDao.find(Integer.parseInt(id));
        ProductCategory productCategory = productCategoryDao.find(Integer.parseInt(id));
        Product product = new Product(name, Float.parseFloat(price), currency, description, productCategory, supplier);
        productDaoSqlite.insert(product);
        Map<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());
        ModelAndView modelAndView = new ModelAndView(params, "admin/productAdd");
        assertSame(modelAndView.getViewName(), productController.adminProductInsert(request, response).getViewName());
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