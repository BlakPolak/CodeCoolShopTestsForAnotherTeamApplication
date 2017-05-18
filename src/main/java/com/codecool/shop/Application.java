package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ConfirmController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;


public class Application {

    private static Application app = new Application();
    private Connection connection;
    private BasketController basketController = null;
    private ProductController productController = null;
    private ConfirmController confirmController = new ConfirmController();

    private Application() {
        basketController = new BasketController();
        productController = new ProductController();
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

    private void appRoutes() {

        before((request, response) -> {
            boolean newSession = request.session().isNew();
            // ... check if authenticated
            if (newSession) {
                Basket basket = new Basket();
                request.session().attribute("basket",basket);
            }
        });

        get("admin/addproduct", this.productController::adminProductInsert);
        post("admin/addproduct", this.productController::adminProductInsert);
        get("admin/editproduct/:id", this.productController::adminProductEdit);
        get("admin/updateproduct/:id", this.productController::adminProductEdit);
        post("admin/updateproduct/:id", this.productController::adminProductEdit);
        get("admin/products", this.productController::adminshowAll);
        get("admin", this.productController::adminshowAll);

        get("/basket", basketController::renderBasket);
        get("/basket/:id/:quantity/add", basketController::addToCartAction);
        get("/basket/:id/:quantity/delete", basketController::deleteFromCartAction);

        get("/hello", (req, res) -> "Hello World");

        get("/confirm", confirmController::displayConfirmForm);
        post("/confirm", confirmController::processOrder);


        get("/products", this.productController::index);
        get("/products/:id", this.productController::showProduct);
        post("/products/byCategory/", this.productController::indexFilter);

    }

    public static void run(){

        Application.getApp().setConnection();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }

}
