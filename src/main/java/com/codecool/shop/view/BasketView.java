package com.codecool.shop.view;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.text.MessageFormat;
import java.util.List;

public class BasketView {
    public void displayBasket(Basket basket) {
        System.out.println("Your basket contains: ");
        for (BasketItem item : basket.getItems()) {
            System.out.println(item);
        }
    }

    public void displayBasketSummary(Basket basket) {
        Float totalCosts = basket.getBasketPrice();
        Integer itemAmount = basket.getItems().size();
        Integer totalUnits = basket.getTotalUnits();
        System.out.println(MessageFormat.format("Basket summary:\ntotal price: {0}PLN\ntotal units in basket: {1}\ntotal items in basket: {2}",
                totalCosts, totalUnits, itemAmount));
    }
}
