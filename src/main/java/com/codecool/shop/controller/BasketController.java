package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.BasketView;
import com.codecool.shop.view.ProductView;
import com.codecool.shop.view.UserInput;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
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

    public String renderBasket(Request req, Response res) {

        Basket basket = req.session().attribute("basket");

        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        Supplier supplier = new Supplier("Supplier", "Description");
        Product product = new Product("Jakis", 15.2839120f, "PLN", "Desc", category, supplier);
        product.setId(1);

        basket.add(product, 1);

        Map params = new HashMap<>();
        params.put("basket", basket);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }
}
