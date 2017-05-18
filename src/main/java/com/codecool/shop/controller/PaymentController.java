package com.codecool.shop.controller;

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


    public String displayPaymentForm(Request req, Response res) {
        Map params = new HashMap<>();
        params.put("basket", req.session().attribute("basket"));
        ModelAndView render = new ModelAndView(params, "product/payment");
        return new ThymeleafTemplateEngine().render(render);

    }
}
