package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderController {

    private OrderDao orderDao = new OrderDaoSqlite();

    public String showAll(Request request, Response response) {

        List<Order> orderList = orderDao.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("orders", orderList);
        System.out.println(orderList.get(0).getBasket().getItems());
        ModelAndView render = new ModelAndView(params, "admin/orderslist");
        return new ThymeleafTemplateEngine().render(render);
    }
}
