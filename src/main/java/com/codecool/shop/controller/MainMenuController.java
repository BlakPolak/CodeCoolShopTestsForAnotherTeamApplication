package com.codecool.shop.controller;

import com.codecool.shop.view.UserInput;

public class MainMenuController {
    static ProductController productController = new ProductController();

    public void mainMenuAction(){
        System.out.println("1. List all products");
        System.out.println("2. List products by category");
        System.out.println("3. List products by supplier");
        System.out.println("Select option");
        Integer option = UserInput.getUserInput();
        switch (option){
            case 1:
                productController.displayList();
                break;
            case 2:
                productController.listProductByCategory();
                break;
            case 3:
                productController.listProductBySupplier();
                break;
            default:
                System.out.println("Wrong option");
        }
    }
}
