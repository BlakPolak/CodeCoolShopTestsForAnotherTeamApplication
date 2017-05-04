package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductView {

    public void displayList(List<Product> products){
        for(Product product : products){
            System.out.println(product.toString());
        }
    }

    public void displayCategoryList(List<ProductCategory> productCategoryList){
        System.out.println("Product categories");
        for(ProductCategory productCategory: productCategoryList){
            System.out.println(productCategory.toString());
        }
    }
}
