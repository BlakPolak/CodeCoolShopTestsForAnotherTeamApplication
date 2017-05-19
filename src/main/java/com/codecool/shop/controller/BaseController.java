package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

abstract class BaseController {

     String render(String filePath){
         Map<String, Object> params = new HashMap<>();
         return render(filePath, params);
     }

     String render(String filePath, Map<String, Object> params){
         return new ThymeleafTemplateEngine().render(new ModelAndView(params, filePath));
     }
}
