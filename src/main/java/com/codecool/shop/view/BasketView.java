package com.codecool.shop.view;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.util.List;

public class BasketView {
    public void displayBasket(Basket basket) {
        System.out.println("Your basket contains: ");
        for (BasketItem item : basket.getItems()) {
            System.out.println(item);
        }
    }
}
