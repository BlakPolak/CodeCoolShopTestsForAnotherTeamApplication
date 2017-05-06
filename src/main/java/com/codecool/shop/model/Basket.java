package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<BasketItem>();

    public void add(Product product, Integer quantity) {

        boolean productExists = false;

        for (BasketItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
            }
        }
        if (!productExists) {
            BasketItem item = new BasketItem(product, quantity);
            this.getItems().add(item);
        }

    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String itemsNames = "";
        for (BasketItem item : this.getItems()) {
            itemsNames += item.getProduct().getName() + "- quantity: " + Integer.toString(item.getQuantity()) + "\n";
        }
        return "Basket: " +
                "items: \n" + itemsNames;
    }
}
