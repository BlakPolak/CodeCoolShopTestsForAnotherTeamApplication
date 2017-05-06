package com.codecool.shop.view;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.util.List;

public class BasketView {
    public void displayBasket(Basket basket) {
        for (BasketItem item : basket.getItems()) {
//            System.out.println(item.getProduct().getName() + "- quantity: " + Integer.toString(item.getQuantity()));
            System.out.println(item);
        }
    }
}
