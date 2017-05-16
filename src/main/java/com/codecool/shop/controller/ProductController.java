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
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

    public ModelAndView showAll(Response response, Request request){
        List<Product> products = productDao.getAll();
        Map<String, Object> model= new HashMap<>();
        model.put("products", products);
        return new ModelAndView(model, "product/index");
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


}
