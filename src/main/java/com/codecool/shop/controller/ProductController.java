package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.view.ProductView;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView view = new ProductView();

    public void displayList(){
        List<Product> products = productDao.getAll();
        view.displayList(products);
    }

    public void listProductByCategory(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for(int i = 1; i<4; i++){
            String name = "Category" + Integer.toString(i);
            ProductCategory productCategory = new ProductCategory(name, "department", "description");
            productCategoryList.add(productCategory);
        }

    }


}
