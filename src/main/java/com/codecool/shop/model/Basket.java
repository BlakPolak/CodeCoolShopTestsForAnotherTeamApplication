package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<BasketItem>();
    private Float basketPrice = 0.00f;
//    do I need this as a atribute or method should be enough

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

    public Float getBasketPrice() {
        for (BasketItem item: this.getItems()) {
            this.basketPrice += item.getAllPrice();
        }
        return basketPrice;
    }

    public Integer getTotalUnits() {
        Integer units = 0;
        for (BasketItem item : this.getItems()) {
            units += item.getQuantity();

        }
        return  units;
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
