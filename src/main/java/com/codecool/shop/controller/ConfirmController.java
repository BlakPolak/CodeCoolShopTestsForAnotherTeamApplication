package com.codecool.shop.controller;

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

    public String displayConfirmForm(Request req, Response res) {
        Map params = new HashMap<>();
        ModelAndView render = new ModelAndView(params, "product/confirm");
        return new ThymeleafTemplateEngine().render(render);

    }

}
