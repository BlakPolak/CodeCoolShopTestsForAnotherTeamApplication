package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


public class ConfirmController extends BaseController{

    private UserDao userDao;
    private OrderDao orderDao;
    private BasketDao basketDao;


    public ConfirmController(Connection connection) {
        this.userDao = new UserDaoSqlite(connection);
        this.orderDao = new OrderDaoSqlite(connection);
        this.basketDao = new BasketDaoSqlite(connection);
    }

    public ModelAndView displayConfirmForm(Request req, Response res) {
        Basket basket = req.session().attribute("basket");
        if (basket.getItems().size() == 0) {
            res.redirect("/products");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("basket", req.session().attribute("basket"));
        return new ModelAndView(params, "product/confirm");

    }

    public String processOrder(Request req, Response res) {
        Integer userId = saveUser(req, res);
        Integer orderId = saveOrder(userId);
        saveBasket(req, res, orderId);
        req.session().attribute("orderId", orderId);
        req.session().attribute("userId", userId);
        res.redirect("/payment");
        return "";
    }

    private void saveBasket(Request req, Response res, Integer orderId) {
        basketDao.add(req.session().attribute("basket"), orderId);
    }

    private Integer saveUser(Request req, Response res) {
        String firstName = req.queryParams("firstName");
        String lastName = req.queryParams("lastName");
        String adres = req.queryParams("adres");
        String phone = req.queryParams("phone");
        String email = req.queryParams("email");
        return userDao.add(new User(firstName, lastName, adres, phone, email));
    }

    private Integer saveOrder(Integer userId) {
        return orderDao.add(new Order(userId));

    }

}
