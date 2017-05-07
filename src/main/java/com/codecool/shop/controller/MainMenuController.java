package com.codecool.shop.controller;


import com.codecool.shop.view.UserInput;


public class MainMenuController {

    static ProductController productController = new ProductController();
    static BasketController basketController = new BasketController();

    public static void mainMenuAction() {
        boolean shopping = true;
        while (shopping) {
            System.out.println("1. List all products.");
            System.out.println("2. List product by category.");
            System.out.println("3. List product by supplier.");
            System.out.println("4. Add product to basket.");
            System.out.println("5. Review basket.");
            System.out.println("6. Edit basket.");
            System.out.println("7. Confirm and pay.");

            System.out.println("0. Exit.");
            System.out.println("Select option:");

            Integer option = UserInput.getUserInput();

            switch (option) {
                case 1:
                    System.out.println("Product list: ");
                    productController.showProducts();
                    break;
                case 2:
                    System.out.println("Product list by category:");
                    productController.showProductsByCategory();
                    break;
                case 3:
                    System.out.println("Product list by supplier:");
                    productController.showProductsBySupplier();
                    break;
                case 4:
                    System.out.println("Add item to basket:");
                    basketController.addToCartAction();
                    break;
                case 5:
                    System.out.println("Review basket:");
                    basketController.reviewBasket();
                    break;
                case 6:
                    System.out.println("Edit basket:");
                    basketController.editBasket();
                    break;
                case 7:
                    System.out.println("Confirm and pay:");
                    basketController.confirmAndPay();
                    break;
                case 0:
                    System.out.println("Exit program");
                    shopping = false;
                    break;
                default:
                    System.out.println("Option not found.");

            }
        }
    }
}
