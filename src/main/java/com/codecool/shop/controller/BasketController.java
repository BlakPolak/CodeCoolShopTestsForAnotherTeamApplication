package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.List;

public class BasketController {
    private ProductDao productDao = new ProductDaoSqlite();
    private ProductView productView = new ProductView();
    private Basket basket = new Basket();

    public void addToCartAction(){
        List<Product> products = this.productDao.getAll();
        Integer userInput = UserInput.getUserInput();
        Product product = productDao.find(userInput);
        basket.add(product, 1);
    }
}
