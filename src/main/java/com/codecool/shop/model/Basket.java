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
        if(quantity < 1) throw new IllegalArgumentException("Quantity can't be lower than 1.");
        if(product == null) throw new NullPointerException("Product has to have a value.");
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
        if(quantity < 1) throw new IllegalArgumentException("Quantity can't be lower than 1.");
        if(product == null) throw new NullPointerException("Product has to have a value.");

        for (BasketItem basketItem : basketItems) {
            if (basketItem.getProduct().getId() == product.getId()) {
                Integer newQuantity = basketItem.getQuantity() - quantity;
                if (newQuantity > 0) {
                    basketItem.setQuantity(newQuantity);
                } else {
                    basketItem.setQuantity(0);
                    basketItems.remove(basketItem);
                    this.setItems(basketItems);
                }
                break;
            }
        }
    }
    public BasketItem getItemById (Integer id) {
        if(id < 0) throw new IllegalArgumentException("Id can't be lower than 0.");
        for (BasketItem basketItem : this.getItems()) {
            if (basketItem.getProduct().getId() == id) {
                return basketItem;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Basket.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        return true;
    }
}
