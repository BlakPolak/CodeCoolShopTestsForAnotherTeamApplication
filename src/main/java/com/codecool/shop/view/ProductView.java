package com.codecool.shop.view;

import com.codecool.shop.model.Product;

import java.util.List;

public class ProductView {

    public void displayList(List<Product> products){
        for(Product product : products){
            System.out.println(product.toString());
        }
    }
}
