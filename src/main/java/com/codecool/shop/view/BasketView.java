package com.codecool.shop.view;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.util.List;

public class BasketView {

    public void displayBasket(List<BasketItem> basketItems){
        for(BasketItem basketItem: basketItems){
            System.out.println(basketItem.toString());
        }
    }
}
