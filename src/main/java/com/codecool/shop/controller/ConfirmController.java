package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.model.Basket;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pati on 16.05.17.
 */
public class ConfirmController {

    private static Basket basket = null;
    private ProductDaoSqlite productDaoSqlite = new ProductDaoSqlite();

    public String displayConfirmForm(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("basket", getBasket());
        ModelAndView render = new ModelAndView(params, "product/confirm");
        return new ThymeleafTemplateEngine().render(render);

    }

    private Basket getBasket() {
        if (basket.equals(null)) {
            basket = new Basket();
            basket.add(productDaoSqlite.find(1), 5);
            basket.add(productDaoSqlite.find(2), 10);
            basket.add(productDaoSqlite.find(3), 15);
        }
        return basket;

    }

}
