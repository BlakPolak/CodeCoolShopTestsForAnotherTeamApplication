package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public void add(Product product, Integer quantity){
        Boolean productExists = false;
        for(BasketItem item: this.getItems()){
            if(item.getProduct().getId() == product.getId()){
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
            }
        }
        if(!productExists){
            BasketItem basketItem = new BasketItem(product, quantity);
            this.getItems().add(basketItem);
        }
    }



}
