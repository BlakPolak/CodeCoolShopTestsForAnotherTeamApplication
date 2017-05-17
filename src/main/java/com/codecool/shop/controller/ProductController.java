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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private static ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();
    private static ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private static SupplierDao supplierDao = new SupplierDaoSqlite();

    public void displayList(){
        List<Product> products = productDao.getAll();
        view.displayList(products);
    }

    public void listProductByCategory(){
        List<ProductCategory> productCategoryList = productCategoryDao.getAll();
        view.displayCategoryList(productCategoryList);
        Integer categoryId = UserInput.getUserInput();
        ProductCategory category = productCategoryDao.find(categoryId);
        List<Product> products = productDao.getBy(category);
        view.displayList(products);
    }

    public void listProductBySupplier(){
        List<Supplier> suppliers= supplierDao.getAll();
        view.displaySupplierList(suppliers);
        Integer supplierID = UserInput.getUserInput();
        Supplier supplier = supplierDao.find(supplierID);
        List<Product> products = productDao.getBy(supplier);
        view.displayList(products);
    }

    public static ModelAndView adminProductAdd(Request request, Response response) {

        if (!request.queryParams().isEmpty()) {

              String name = request.queryParams("name");
              Float price = Float.parseFloat(request.queryParams("price"));
//            String description = request.queryParams("description");
//            String currency = request.queryParams("currency");
//            System.out.println("supplier");
//            Supplier supplier = supplierDao.find(Integer.parseInt(request.queryParams("supplier")));
//            System.out.println("category");
//            ProductCategory category = productCategoryDao.find(Integer.parseInt(request.queryParams("category")));

           // Product product = new Product(name, price, currency, description, category, supplier);

           // System.out.println(product);

            //productDao.add(product);
        }

        Map params = new HashMap<>();
        return new ModelAndView(params,"admin/productAdd");
    }


}
