package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public void displayList(){
        ProductDao productDao = new ProductDaoSqlite();
        List<Product> products = productDao.getAll();
        for(Product product : products){
            System.out.println(product.toString());
        }
    }


}
