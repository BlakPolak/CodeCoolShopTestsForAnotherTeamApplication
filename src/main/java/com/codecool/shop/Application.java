package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ConfirmController;
import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.*;
import static spark.Spark.notFound;


public class Application {

    private static Application app = new Application();
    private Connection connection;
    private BasketController basketController = null;
    private OrderController orderController = null;
    private ProductController productController = null;
    private ConfirmController confirmController = new ConfirmController();
    private PaymentController paymentController = new PaymentController();

    private Application() {
        basketController = new BasketController();
        productController = new ProductController();
        orderController = new OrderController();
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

        path("/admin", () -> {
            get("/orders", this.orderController::showAll);
            get("/remove/:id", this.productController::removeProduct);
            get("/addproduct", this.productController::adminProductInsert);
            post("/addproduct", this.productController::adminProductInsert);
            get("/editproduct/:id", this.productController::adminProductEdit);
            get("/updateproduct/:id", this.productController::adminProductEdit);
            post("/updateproduct/:id", this.productController::adminProductEdit);
            get("/products", this.productController::adminshowAll);
            get("/products/search", this.productController::adminshowAll);
            get("/", this.productController::adminshowAll);
        });

        get("/basket", basketController::renderBasket);
        post("/basket/add", basketController::addToCartAction);
        get("/basket/:id/:quantity/delete", basketController::deleteFromCartAction);
        post("/basket/remove", basketController::deleteFromCartAction);

        get("/hello", (req, res) -> "Hello World");

        get("/confirm", confirmController::displayConfirmForm);
        post("/confirm", confirmController::processOrder);
        get("payment", paymentController::displayPaymentForm);
        post("payment", paymentController::processPayment);


        get("/products", this.productController::index);
        get("/products/:id", this.productController::showProduct);
        post("/products/byCategory/", this.productController::indexFilter);

        notFound(((request, response) -> {
            response.redirect("/products");
            return null;
        } ));
    }

    public static void run(){

        Application.getApp().setConnection();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }

}
