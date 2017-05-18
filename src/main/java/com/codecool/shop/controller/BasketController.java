package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class BasketController extends BaseController{
    private ProductDao productDao = new ProductDaoSqlite();

    public String addToCartAction(Request req, Response res){
        Basket basket = req.session().attribute("basket");
        Integer id = Integer.parseInt(req.params("id"));

        Product product = productDao.find(id);
        basket.add(product, 1);

        res.redirect("/products");
        return null;
    }

    public String renderBasket(Request req, Response res) {
        Basket basket = req.session().attribute("basket");
        Map<String,Object> params = new HashMap<>();
        params.put("basket", basket);
        return this.render("product/basket", params);
    }

    public String deleteFromCartAction(Request req, Response res) {
        Basket basket = req.session().attribute("basket");

        String baseRoute = req.headers("Referer");
        Integer id = Integer.parseInt(req.params("id"));
        Integer quantity = Integer.parseInt(req.params("quantity"));

        Product product = productDao.find(id);
        basket.delete(product, quantity);

        res.redirect(baseRoute);
        return null;
    }
}
