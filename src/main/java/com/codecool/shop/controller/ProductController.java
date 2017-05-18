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

public class ProductController extends BaseController{
    private ProductView view = new ProductView();
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

    public String index(Request request, Response response){
        Basket basket = request.session().attribute("basket");

        List<Product> products = productDao.getAll();
        List<ProductCategory> productCategories = productCategoryDao.getAll();
        List<Supplier> suppliers = supplierDao.getAll();
        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        model.put("suppliers", suppliers);
        model.put("categories", productCategories);
        model.put("basket", basket);
        return this.render("product/index", model);
    }

    public String indexFilter(Request request, Response response){
        Basket basket = request.session().attribute("basket");

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
        model.put("basket", basket);
        return this.render("product/index", model);
    }

    public void listProductBySupplier(){
        List<Supplier> suppliers= supplierDao.getAll();
        view.displaySupplierList(suppliers);
        Integer supplierID = UserInput.getUserInput();
        Supplier supplier = supplierDao.find(supplierID);
        List<Product> products = productDao.getBy(supplier);
        view.displayList(products);
    }

    public String adminProductEdit(Request request, Response response) {

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
        Map params = new HashMap<>();
        params.put("product", productDao.find(Integer.parseInt(request.params(":id"))));
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());

        ModelAndView render = new ModelAndView(params, "admin/productEdit");
        return new ThymeleafTemplateEngine().render(render);
    }

    public String adminshowAll(Request request, Response response) {

        Map params = new HashMap<>();
        params.put("products", productDao.getAll());

        ModelAndView render = new ModelAndView(params, "admin/productlist");
        return new ThymeleafTemplateEngine().render(render);
    }

    public String adminProductInsert(Request request, Response response) {

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

        Map params = new HashMap<>();
        params.put("categories", productCategoryDao.getAll());
        params.put("suppliers", supplierDao.getAll());
        ModelAndView render = new ModelAndView(params, "admin/productAdd");
        return new ThymeleafTemplateEngine().render(render);
    }


    public String showProduct(Request request, Response response){
        Basket basket = request.session().attribute("basket");
        Integer productId = Integer.parseInt(request.params("id"));
        Product product = this.productDao.find(productId);
        Map<String, Object> model= new HashMap<>();
        model.put("product",product);
        model.put("basket", basket);
        ModelAndView render = new ModelAndView(model, "product/product");
        return new ThymeleafTemplateEngine().render(render);
    }

}
