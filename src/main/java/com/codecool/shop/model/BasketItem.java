package com.codecool.shop.model;


public class BasketItem {
    private Product product;
    private Integer quantity;
    private Float allPrice;


    BasketItem(Product product, Integer quantity) {
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setAllPrice();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getAllPrice() {
        this.setAllPrice();
        return allPrice;
    }

    public void setAllPrice() {
        this.allPrice = this.getQuantity() * this.getProduct().getDefaultPrice();
    }

    @Override
    public String toString() {
        return "Product: " + this.getProduct().getName() +
               ", unit price: " + this.getProduct().getPrice() +
                ", quantity: " + this.getQuantity() +
                ", allPrice: " + this.getAllPrice() + this.getProduct().getDefaultCurrency();
    }
}
