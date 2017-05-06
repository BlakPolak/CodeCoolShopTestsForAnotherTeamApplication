package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<BasketItem>();
    private Float basketPrice = 0.00f;  //    do I need this as a atribute or method should be enough
    private Boolean confirm = false;
    private Boolean payed = false;

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

    public void update(Integer item_id, Integer newAmount) {
        if (newAmount <= 0) {
            this.getItems().remove(item_id - 1);
        } else {
            this.getItems().get(item_id - 1).setQuantity(newAmount);
        }
    }

    public Float getBasketPrice() {
        this.basketPrice = 0.00f;
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
