package com.codecool.shop.view;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductView {

    public void displayProductsList(List<Product> products) {
        for (Product prod : products) {
            System.out.println(prod);
        }
    }

    public void displayCategoriesList(List<ProductCategory> categories) {
        for (ProductCategory cat : categories) {
            System.out.println(cat.getId() + ") " + cat.getName());
        }
    }

    public void displaySuppliersList(List<Supplier> suppliers) {
        for (Supplier sup : suppliers) {
            System.out.println(sup.getId() + ") " + sup.getName());
        }
    }

}
