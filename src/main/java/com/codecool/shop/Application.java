package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ConfirmController;
import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.*;
import java.sql.SQLException;

import static spark.Spark.*;
import static spark.Spark.notFound;


public class Application {

    private static Application app = new Application();
    private BasketController basketController;
    private OrderController orderController;
    private ProductController productController;
    private ConfirmController confirmController;
    private PaymentController paymentController;

    private Application() {
        setConnection();
        this.basketController = new BasketController(SqliteJDBCConnector.getConnection());
        this.productController = new ProductController(SqliteJDBCConnector.getConnection());
        this.orderController = new OrderController(SqliteJDBCConnector.getConnection());
        this.confirmController = new ConfirmController(SqliteJDBCConnector.getConnection());
        this.paymentController = new PaymentController(SqliteJDBCConnector.getConnection());
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Thread.sleep(200);
                SqliteJDBCConnector.getConnection().close();
                System.out.println("Shouting down ...");

            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }));
    }


    public static Application getApp() {
        return app;
    }


    private void setConnection(){
        try {
            SqliteJDBCConnector.setConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            e.printStackTrace();
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

        Application.getApp();
        Application.getApp().appSettings();
        Application.getApp().appRoutes();
    }

}
