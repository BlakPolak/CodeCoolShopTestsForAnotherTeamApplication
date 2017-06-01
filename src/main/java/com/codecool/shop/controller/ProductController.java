package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import java.sql.Connection;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController extends BaseController{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductController(Connection connection) {
        this.productDao = new ProductDaoSqlite(connection);
        this.productCategoryDao = new ProductCategoryDaoSqlite(connection);
        this.supplierDao = new SupplierDaoSqlite(connection);
    }

    public ModelAndView index(Request request, Response response){
        Basket basket = request.session().attribute("basket");
        List<Product> products = productDao.getAll();
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        model.put("suppliers", suppliers);
        model.put("categories", productCategories);
        model.put("basket", basket);
        return new ModelAndView(model, "product/index");
    }

    public ModelAndView indexFilter(Request request, Response response){
        String productName = request.queryParams("name");
        String categoryId = request.queryParams("category");
        String supplierId = request.queryParams("supplier");

        Basket basket = request.session().attribute("basket");
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        List<Product> products = this.productDao.getByFilters(productName, categoryId, supplierId);

        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        model.put("suppliers", suppliers);
        model.put("categories", productCategories);
        model.put("basket", basket);
        return new ModelAndView(model, "product/index");
    }

    public ModelAndView removeProduct(Request request, Response response) {

        productDao.remove(Integer.parseInt(request.params(":id")));
        response.redirect("../../admin/products");

        Map params = new HashMap<>();
        return new ModelAndView(params, "admin/productEdit");
    }
    public ModelAndView adminProductEdit(Request request, Response response) {

        if (!request.queryParams().isEmpty()) {
            Integer id = Integer.parseInt(request.params(":id"));
            String name = request.queryParams("name");
            Float price = Float.parseFloat(request.queryParams("price"));
            String description = request.queryParams("description");
            String currency = "PLN";
            Supplier supplier = supplierDao.find(Integer.parseInt(request.queryParams("supplier")));
            ProductCategory category = productCategoryDao.find(Integer.parseInt(request.queryParams("category")));
            Product product = new Product(id, name, price, currency, description, category, supplier);
            productDao.update(product);
            response.redirect("../products");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("product", productDao.find(Integer.parseInt(request.params(":id"))));
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());

        return new ModelAndView(params, "admin/productEdit");
    }

    public ModelAndView adminshowAll(Request request, Response response) {

        Map<String, Object> params = new HashMap<>();
        params.put("products", productDao.getAll());

        return new ModelAndView(params, "admin/productlist");
    }

    public ModelAndView adminProductInsert(Request request, Response response) {

        if (!request.queryParams().isEmpty()) {

            String name = request.queryParams("name");
            Float price = Float.parseFloat(request.queryParams("price"));
            String description = request.queryParams("description");
            String currency = "PLN";
            Supplier supplier = supplierDao.find(Integer.parseInt(request.queryParams("supplier")));
            ProductCategory category = productCategoryDao.find(Integer.parseInt(request.queryParams("category")));
            Product product = new Product(name, price, currency, description, category, supplier);
            productDao.insert(product);
            response.redirect("../admin/products");

        }

        Map<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());
        return new ModelAndView(params, "admin/productAdd");
    }


    public ModelAndView showProduct(Request request, Response response){
        Basket basket = request.session().attribute("basket");
        Integer productId = Integer.parseInt(request.params("id"));
        Product product = this.productDao.find(productId);
        Map<String, Object> model= new HashMap<>();
        model.put("product",product);
        model.put("basket", basket);
        return new ModelAndView(model, "product/product");
    }

}
