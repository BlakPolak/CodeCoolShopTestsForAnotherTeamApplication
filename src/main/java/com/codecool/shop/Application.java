package com.codecool.shop;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;
import static spark.Spark.exception;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {

    private static Application app = new Application();
    private Connection connection;
    private ProductController productController = new ProductController();


    private Application() {


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

        get("admin/addproduct", this.productController::adminProductInsert);
        post("admin/addproduct", this.productController::adminProductInsert);
        get("admin/products", this.productController::adminshowAll);

        get("/products", this.productController::showAll);
        post("/products/byCategory/", this.productController::indexFilter);

    }

    public static void run(){

        Application.getApp().setConnection();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }

}
