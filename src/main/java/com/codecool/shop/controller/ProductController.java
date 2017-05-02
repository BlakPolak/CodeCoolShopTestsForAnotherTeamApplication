package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;

import java.util.List;

public class ProductController {

    public void displayList(){
        ProductDao productDao = new ProductDaoSqlite();
        List<Product> products = productDao.getAll();
        for(Product product : products){
            System.out.println(product.toString());
        }
    }


}
