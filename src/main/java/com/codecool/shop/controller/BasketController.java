package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;
import com.codecool.shop.model.Product;

import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class BasketController {
    private ProductDao productDao = new ProductDaoSqlite();

    public String addToCartAction(Request req, Response res){
        Basket basket = req.session().attribute("basket");
        Integer id = Integer.parseInt(req.queryParams("id"));
        Product product = productDao.find(id);
        basket.add(product, 1);
        return basket.getPrice().toString();
    }

    public String renderBasket(Request req, Response res) {
        Basket basket = req.session().attribute("basket");
        Map<String,Object> params = new HashMap<>();
        params.put("basket", basket);
        return new ThymeleafTemplateEngine().render(new ModelAndView(params, "product/basket"));
    }

    public String deleteFromCartAction(Request req, Response res) {
        Basket basket = req.session().attribute("basket");
        Integer id = Integer.parseInt(req.queryParams("id"));
        BasketItem basketItem = basket.getItemById(id);
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Product product = productDao.find(id);

        Map<String,Object> params = new HashMap<>();
        basket.delete(product, quantity);
        if (basketItem.getQuantity() > 0) {
            Float itemPrice = basketItem.getPrice();
            params.put("itemPrice", itemPrice);
        }

        params.put("id", id);
        params.put("quantity", basketItem.getQuantity());
        params.put("totalPrice", basket.getPrice());

        Gson gson = new Gson();
        String json = gson.toJson(params);

        return json;
    }
}
