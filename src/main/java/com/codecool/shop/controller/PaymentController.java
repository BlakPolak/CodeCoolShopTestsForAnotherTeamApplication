package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.UserDaoSqlite;
import com.codecool.shop.model.SendEmail;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pati on 18.05.17.
 */
public class PaymentController {

    private UserDao userDao = new UserDaoSqlite();
    private SendEmail sendEmail = new SendEmail();


    public String displayPaymentForm(Request req, Response res) {
        Integer userId = req.session().attribute("userId");
        if (userId == null) {
            res.redirect("/products");
        }
        Map params = new HashMap<>();
        params.put("basket", req.session().attribute("basket"));
        ModelAndView render = new ModelAndView(params, "product/payment");
        return new ThymeleafTemplateEngine().render(render);

    }

    public String processPayment(Request req, Response res) {
        Integer m = req.session().attribute("userId");
        if (m != null ) {
            User user = userDao.find(req.session().attribute("userId"));
            sendEmail.send(user, req.session().attribute("basket"));
        }
        res.redirect("/products");

        return "";
    }
}
