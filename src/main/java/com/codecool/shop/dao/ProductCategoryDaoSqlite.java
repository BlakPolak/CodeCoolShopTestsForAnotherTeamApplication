package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 04.05.17.
 */
public class ProductCategoryDaoSqlite  implements ProductCategoryDao{
    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        for(int i = 1; i<4; i++){
            String name = "Category" + Integer.toString(i);
            ProductCategory productCategory = new ProductCategory(name, "department", "description");
            productCategoryList.add(productCategory);
        }
        return productCategoryList;
    }
}
