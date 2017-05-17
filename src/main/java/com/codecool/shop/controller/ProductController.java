package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoSqlite;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.SupplierDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private static ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();
    private static ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private static SupplierDao supplierDao = new SupplierDaoSqlite();

    public String showAll(Request request, Response response){
        List<Product> products = productDao.getAll();
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        model.put("suppliers", suppliers);
        model.put("categories", productCategories);
        ModelAndView render = new ModelAndView(model, "product/index");
        return new ThymeleafTemplateEngine().render(render);
    }

    public String indexFilter(Request request, Response response){
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        String productName = request.queryParams("name");
        String categoryId = request.queryParams("category");
        String supplierId = request.queryParams("supplier");
        List<Product> products = this.productDao.getByFilters(productName, categoryId, supplierId);
        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        model.put("suppliers", suppliers);
        model.put("categories", productCategories);
        ModelAndView render = new ModelAndView(model, "product/index");
        return new ThymeleafTemplateEngine().render(render);
    }


    public void listProductBySupplier(){
        List<Supplier> suppliers= supplierDao.getAll();
        view.displaySupplierList(suppliers);
        Integer supplierID = UserInput.getUserInput();
        Supplier supplier = supplierDao.find(supplierID);
        List<Product> products = productDao.getBy(supplier);
        view.displayList(products);
    }

    public String adminshowAll(Request request, Response response) {

        Map params = new HashMap<>();
        params.put("products", productDao.getAll());

        ModelAndView render = new ModelAndView(params, "admin/productlist");
        return new ThymeleafTemplateEngine().render(render);

    }

    public String adminProductAdd(Request request, Response response) {

        if (!request.queryParams().isEmpty()) {

            String name = request.queryParams("name");
            Float price = Float.parseFloat(request.queryParams("price"));
            String description = request.queryParams("description");
            String currency = "PLN";
            Supplier supplier = supplierDao.find(Integer.parseInt(request.queryParams("supplier")));
            ProductCategory category = productCategoryDao.find(Integer.parseInt(request.queryParams("category")));
            Product product = new Product(name, price, currency, description, category, supplier);
            productDao.insert(product);

        }

        Map params = new HashMap<>();
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());
        ModelAndView render = new ModelAndView(params, "admin/productAdd");
        return new ThymeleafTemplateEngine().render(render);
    }

}
