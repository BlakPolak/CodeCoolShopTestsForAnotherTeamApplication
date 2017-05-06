package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

    public void showProducts() {

        List<Product> products = productDao.getAll();
        view.displayProductsList(products);

    }

    public void showProductsByCategory() {

        List<ProductCategory> categories = productCategoryDao.getAll();
        view.displayCategoriesList(categories);

        System.out.println("Select category: ");
        Integer categoryId = UserInput.getUserInput();

        ProductCategory category = productCategoryDao.find(categoryId);

        List<Product> products = productDao.getBy(category);
        view.displayProductsList(products);

    }

    public void showProductsBySupplier() {
        List<Supplier> suppliers = supplierDao.getAll();
        view.displaySuppliersList(suppliers);

        System.out.println("Select supplier: ");
        Integer supplierId = UserInput.getUserInput();

        Supplier supplier = supplierDao.find(supplierId);
        System.out.println(supplier.getName());

        List<Product> products =productDao.getBy(supplier);
        view.displayProductsList(products);


    }
}
