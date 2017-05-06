package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoSqlite implements ProductDao{
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        Product product = null;
        ProductCategory productCategory = new ProductCategory("dupeczka", "deparatamencik", "descryptionik");
        Supplier supplier = new Supplier("Supcio", "Super supplier");

        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products where id = " + Integer.toString(id));
            product = new Product(
                rs.getString("name"),
                rs.getFloat("price"),
                "PLN",
                rs.getString("description"),
                productCategory,
                supplier
            );
            product.setId(rs.getInt("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return product;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {

        List<Product> products = new ArrayList<Product>();
        ProductCategory category = new ProductCategory("Category", "dep 1", "Desc");
        Supplier supplier = new Supplier("Supcio", "Super supplier");

        try {
//          Connection connection = SqliteJDBCConnector.connection();
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        category,
                        supplier
                );
                product.setId(rs.getInt("id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<Product>();
        ProductCategory productCategory = new ProductCategory("dupeczka", "deparatamencik", "descryptionik");

        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products WHERE supplier_id = " + Integer.toString(supplier.getId()));
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategory,
                        supplier
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;

    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<Product>();
        Supplier supplier = new Supplier("Supcio", "Super supplier");

        try {
//          Connection connection = SqliteJDBCConnector.connection();
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products WHERE category_id = " + Integer.toString(productCategory.getId()));
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategory,
                        supplier
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

}
