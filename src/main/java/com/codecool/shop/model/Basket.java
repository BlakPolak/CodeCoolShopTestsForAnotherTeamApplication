package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Basket{
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

    public Float getPrice() {
        Float basketPrice = 0.0f;
        for (BasketItem item: items) {
            basketPrice += item.getPrice();
        }

        double roundOff = Math.round(basketPrice * 100.0) / 100.0;
        return (float) roundOff;
    }

    public Float getPriceNetto() {

        return getPrice() / 1.23f;
    }


    public void delete(Product product, int quantity) {
        List<BasketItem> basketItems =  this.getItems();

        for (BasketItem basketItem : basketItems) {
            if (basketItem.getProduct().getId() == product.getId()) {
                Integer newQuantity = basketItem.getQuantity() - quantity;
                if (newQuantity > 0) {
                    basketItem.setQuantity(newQuantity);
                } else {
                    basketItems.remove(basketItem);
                    this.setItems(basketItems);
                }
                break;
            }
        }
    }
}
