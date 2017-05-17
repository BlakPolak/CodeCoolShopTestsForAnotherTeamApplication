package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketController {
    private ProductDao productDao = new ProductDaoSqlite();
    private BasketView basketView = new BasketView();
    private ProductView productView = new ProductView();

    public void addToCartAction(Basket basket){
        List<Product> products = this.productDao.getAll();
        productView.displayList(products);
        Integer userInput = UserInput.getUserInput();
        Product product = productDao.find(userInput);
        basket.add(product, 1);
        basketView.displayBasket(basket.getItems());
    }

    public String renderBasket(Basket basket) {
        Map params = new HashMap<>();
        params.put("basket", basket);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }
}
