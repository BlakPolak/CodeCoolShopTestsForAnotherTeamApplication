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

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

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


}
