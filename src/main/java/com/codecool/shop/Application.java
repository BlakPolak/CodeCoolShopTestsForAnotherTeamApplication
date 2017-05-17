package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.Basket;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {

    private static Application app = new Application();
    private Connection connection;
    private BasketController basketController = null;


    private Application() {
        basketController = new BasketController();
    }

    public static Application getApp() {
        return app;
    }

    public Connection getConnection() {
        return connection;
    }

    private void setConnection(){
        try{
            this.connection = SqliteJDBCConnector.connection();
        } catch (SQLException e){
            System.out.println("Connection to Database error");
            System.exit(1);
        }
    }
    private void appSettings(){
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);
    }

    public void appRoutes(){
        before((request, response) -> {
            boolean newSession = request.session().isNew();
            // ... check if authenticated
            if (newSession) {
                Basket basket = new Basket();
                request.session().attribute("basket",basket);
            }
        });

        get("/basket", (Request req, Response res) -> {
            Basket basket = req.session().attribute("basket");
            return basketController.renderBasket(basket);
        });
    }

    public static void run(){
        Application.getApp().setConnection();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }






}
