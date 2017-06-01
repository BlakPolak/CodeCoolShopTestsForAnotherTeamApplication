package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.OrderDaoSqlite;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.UserDaoSqlite;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.SendEmail;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


public class PaymentController {

    private UserDao userDao;
    private SendEmail sendEmail;
    private OrderDao orderDao;

    public PaymentController(Connection connection) {
        this.orderDao = new OrderDaoSqlite(connection);
        this.sendEmail = new SendEmail();
        this.orderDao = new OrderDaoSqlite(connection);
        this.userDao = new UserDaoSqlite(connection);
    }

    public ModelAndView displayPaymentForm(Request req, Response res) {
        Integer userId = req.session().attribute("userId");
        if (userId == null) {
            res.redirect("/products");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("basket", req.session().attribute("basket"));
        return new ModelAndView(params, "product/payment");

    }

    public String processPayment(Request req, Response res) {
        Integer m = req.session().attribute("userId");
        if (m != null ) {
            Boolean paid = false;
            User user = userDao.find(req.session().attribute("userId"));
            if (req.queryParams("paid") != null) {
                orderDao.updatePaid(req.session().attribute("orderId"));
                paid = true;
            }
            sendEmail.send(user, req.session().attribute("basket"), paid);
        }

        req.session().attribute("basket", new Basket());
        res.redirect("/products");

        return "";
    }
}
