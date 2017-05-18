package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
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
    private ProductDao productDao = new ProductDaoSqlite();
    private UserDao userDao = new UserDaoSqlite();
    private OrderDao orderDao = new OrderDaoSqlite();
    private BasketDao basketDao = new BasketDaoSqlite();

    public String displayConfirmForm(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("basket", getBasket());
        ModelAndView render = new ModelAndView(params, "product/confirm");
        return new ThymeleafTemplateEngine().render(render);

    }

    public String processOrder(Request req, Response res) {
        Basket basketToSave = getBasket();
        Integer userId = saveUser(req, res);
        Integer orderId = saveOrder(userId);
        saveBasket(req, res, orderId);

        return "";
    }

    private void saveBasket(Request req, Response res, Integer orderId) {
          basketDao.add(getBasket(), orderId);
//        basketDao.add(req.session().attribute("basket")), orderId);
    }

    public Integer saveUser(Request req, Response res) {
        String firstName = req.queryParams("firstName");
        String lastName = req.queryParams("lastName");
        String adres = req.queryParams("adres");
        String phone = req.queryParams("phone");
        String email = req.queryParams("email");
        return userDao.add(new User(firstName, lastName, adres, phone, email));
    }

    public Integer saveOrder(Integer userId) {
        return orderDao.add(new Order(userId));

    }



    public Integer createOrder() {
        return 1;
    }

    private Basket getBasket() {
        if (basket == null) {
            basket = new Basket();
            basket.add(productDao.find(1), 5);
            basket.add(productDao.find(2), 10);
            basket.add(productDao.find(3), 15);
        }
        return basket;

    }

}
