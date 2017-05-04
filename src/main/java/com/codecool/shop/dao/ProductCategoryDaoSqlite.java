package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        ProductCategory productCategory = null;
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categories WHERE id = " + Integer.toString(id));
            if(rs.next()){
                productCategory = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("description")
                );
                productCategory.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategory;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categories");
            while (rs.next()){
                ProductCategory productCategory = new ProductCategory(
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("description")
                );
                productCategory.setId(rs.getInt("id"));
                productCategoryList.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productCategoryList;
    }
}
