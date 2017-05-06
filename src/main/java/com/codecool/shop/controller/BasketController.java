package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;

import java.util.List;

public class BasketController {

    ProductDao productDao = new ProductDaoSqlite();
    ProductView view = new ProductView();
    Basket basket = new Basket();
    BasketView basketView = new BasketView();


    public void addToCartAction() {
        List<Product> products = this.productDao.getAll();
        view.displayProductsList(products);

        System.out.println("Choose product id: ");
        Integer productId = UserInput.getUserInput();
        Product product = productDao.find(productId);

        System.out.println("Choose quantity for product: ");
        Integer quantity = UserInput.getUserInput();
        this.basket.add(product, quantity);

        System.out.println("In your basket: ");
        displayBasketItems(this.basket);

    }

    public void displayBasketItems(Basket basket) {
        basketView.displayBasket(basket);
    }

    public void reviewBasket(Basket basket) {


    }
}
