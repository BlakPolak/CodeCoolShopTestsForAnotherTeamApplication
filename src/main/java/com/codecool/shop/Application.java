package com.codecool.shop;


import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {

    private static Application app = new Application();
    private Connection connection;
    private ProductController productController = new ProductController();

    private Application() { }

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

    }
    public static void run(){
        Application.getApp().setConnection();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }






}
