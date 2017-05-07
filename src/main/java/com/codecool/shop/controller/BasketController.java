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
        displayBasketItems();

    }

    public void displayBasketItems() {
        basketView.displayBasket(basket);
//        is this method nessery, why can I use  basketView.displayBasket(basket) alone??
    }

    public void reviewBasket() {
        displayBasketItems();
        basketView.displayBasketSummary(this.basket);
    }

    public void editBasket() {
        displayBasketItems();
        System.out.println("Choose id of item Tou want update: ");
        Integer basketItemId = UserInput.getUserInput();
        System.out.println("Put new amount of items - if 0 product will be removed from basket. ");
        Integer newAmount = UserInput.getUserInput();
        this.basket.update(basketItemId, newAmount);
    }

    public void confirmAndPay() {
        System.out.println("Your basket summary: ");
        basketView.displayBasketSummary(this.basket);
        System.out.println("\n Do you want end shopping and confirm basket? (Y/N):");
        Boolean confirm = UserInput.yesNoInput();
    }
}
