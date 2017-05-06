package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoSqlite implements ProductDao{

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private SupplierDao supplierDao = new SupplierDaoSqlite();

    @Override
    public void add(Product product) {

    }

//    Gdzie stworzyc produktCategoryDao i supllierDao, jesli sa potrzebne do
//        uzyskania kompletnego obiektu produktu - na razie ustawione jako atrybuty klasy
//            productDaoSqlite :/

    @Override
    public Product find(int id) {
        Product product = null;
        ProductCategory category;
        Supplier supplier;
        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products where id = " + Integer.toString(id));
            supplier = supplierDao.find(rs.getInt("supplier_id"));
            category = productCategoryDao.find(rs.getInt("category_id"));
            product = new Product(
                rs.getString("name"),
                rs.getFloat("price"),
                "PLN",
                rs.getString("description"),
                category,
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
        ProductCategory category;
        Supplier supplier;

        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                supplier = supplierDao.find(rs.getInt("supplier_id"));
                category = productCategoryDao.find(rs.getInt("category_id"));
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
        ProductCategory category;

        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products WHERE supplier_id = " + Integer.toString(supplier.getId()));
            while (rs.next()) {
                category = productCategoryDao.find(rs.getInt("category_id"));
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        category,
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
    public List<Product> getBy(ProductCategory category) {
        List<Product> products = new ArrayList<Product>();
        Supplier supplier;

        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("select * from products WHERE category_id = " + Integer.toString(category.getId()));
            while (rs.next()) {
                supplier = supplierDao.find(rs.getInt("supplier_id"));
                Product product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        category,
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
