package com.codecool.shop;


import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ConfirmController;
import com.codecool.shop.controller.OrderController;
import com.codecool.shop.controller.PaymentController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.SQLException;

import static spark.Spark.*;
import static spark.Spark.notFound;


public class Application {

    private static Application app = new Application("jdbc:sqlite:src/main/resources/database.db");
    private BasketController basketController;
    private OrderController orderController;
    private ProductController productController;
    private ConfirmController confirmController;
    private PaymentController paymentController;

    private Application(String path) {
        setConnection(path);
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


    public void setConnection(String path){
        try {
            SqliteJDBCConnector.setConnection(path);
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

        get("/admin/products/search", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminshowAll(req, res));
        });
        post("/admin/updateproduct/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminProductEdit(req, res));
        });
        get("/admin/updateproduct/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminProductEdit(req, res));
        });
        get("/admin/editproduct/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminProductEdit(req, res));
        });
        get("/admin/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminshowAll(req, res));
        });
        get("/admin/remove/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.removeProduct(req, res));
        });

        get("/admin/orders", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(orderController.showAll(req, res));
        });
        get("/admin/addproduct", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminProductInsert(req, res));
        });
        post("/admin/addproduct", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminProductInsert(req, res));
        });
        get("/admin/products", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.adminshowAll(req, res));
        });


        get("/basket", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(basketController.renderBasket(req, res));
        });
        post("/basket/add", (Request req, Response res) -> {
            return basketController.addToCartAction(req, res);
        });

        get("/basket/:id/:quantity/delete", (Request req, Response res) -> {
            return basketController.deleteFromCartAction(req, res);
        });

        post("/basket/remove", (Request req, Response res) -> {
            return basketController.deleteFromCartAction(req, res);
        });

        get("/hello", (req, res) -> "Hello World");

        get("/confirm", (Request req, Response res) -> {
             return new ThymeleafTemplateEngine().render(confirmController.displayConfirmForm(req, res));
        });

        post("/confirm", (Request req, Response res) -> {
            return confirmController.processOrder(req, res);
        });

        get("payment", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(paymentController.displayPaymentForm(req, res));
        });

        post("payment", (Request req, Response res) -> {
            return paymentController.processPayment(req, res);
        });

        get("/products", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.index(req, res));
        });
        get("/products/:id", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.showProduct(req, res));
        });
        post("/products/byCategory/", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(productController.indexFilter(req, res));
        });


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
