package com.codecool.shop.controller;

import com.codecool.shop.view.UserInput;

public class MainMenuController {
    ProductController productController = new ProductController();
    BasketController basketController = new BasketController();

    public void mainMenuAction(){
        System.out.println("1. List all products");
        System.out.println("2. List products by category");
        System.out.println("3. List products by supplier");
        System.out.println("Select option");
        Integer option = UserInput.getUserInput();
        switch (option){
            case 1:
                this.productController.displayList();
                break;
            case 2:
                this.productController.listProductByCategory();
                break;
            case 3:
                this.productController.listProductBySupplier();
                break;
            case 4:
                this.basketController.addToCartAction();
            default:
                System.out.println("Wrong option");
        }
    }
}
