
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
import java.io.IOException;
import java.sql.SQLException;

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
        session = mock(Session.class);
        basket = mock(Basket.class);
        productDao = mock(ProductDao.class);
        productController = new ProductController(connection);
    }

//    @Test
//    void testIfIndexReturnsExpectedModelAndView () {
//        when(request.session()).thenReturn(session);
//        when(session.attribute("basket")).thenReturn(basket);
//        when(productDao.getAll()).thenReturn(Arrays.asList(mock(Product.class), mock(Product.class)));
//        when(productCategoryDao.getAll()).thenReturn(Arrays.asList(mock(ProductCategory.class), mock(ProductCategory.class)));
//        when(supplierDao.getAll()).thenReturn(Arrays.asList(mock(Supplier.class), mock(Supplier.class)));
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("basket", basket);
//        params.put("suppliers", Arrays.asList(mock(Supplier.class), mock(Supplier.class)));
//        params.put("categories", Arrays.asList(mock(ProductCategory.class), mock(ProductCategory.class)));
//        params.put("products", Arrays.asList(mock(Product.class), mock(Product.class)));
//        ModelAndView modelAndView = new ModelAndView(params, "product/index");
//        assertSame(modelAndView.getViewName(), productController.index(request, response).getViewName());
//    }

    @Test
    void testIfIndexFilterReturnsExpectedModelAndView () {
        when(request.session()).thenReturn(session);
        Map<String, Object> params = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(params, "product/index");
        assertSame(modelAndView.getViewName(), productController.indexFilter(request, response).getViewName());
    }

    @Test
    void testIfRemoveProductActuallyRemoveProduct () {
        when(request.session()).thenReturn(session);
        Map<String, Object> params = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView(params, "admin/productEdit");
        assertSame(modelAndView.getViewName(), productController.removeProduct(request, response).getViewName());

    }

    @Test
    void testIfRenderAdminShowAllShowExpectedView () {
        Map<String, Object> params = new HashMap<>();
        params.put("products", productDao.getAll());
        ModelAndView modelAndView = new ModelAndView(params, "admin/productlist");
        assertSame(modelAndView.getViewName(), productController.adminshowAll(request, response).getViewName());
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
