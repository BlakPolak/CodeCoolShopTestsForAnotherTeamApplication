package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.model.Order;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderController {

    private OrderDao orderDao;

    public OrderController(Connection connection) {
        this.orderDao = new OrderDaoSqlite(connection);
    }

    public ModelAndView showAll(Request request, Response response) {

        List<Order> orderList = orderDao.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("orders", orderList);
        return new ModelAndView(params, "admin/orderslist");
    }
}
