package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoSqlite extends BaseDao implements ProductDao {
    private static ProductCategoryDao productCategoryDao = new ProductCategoryDaoSqlite();
    private static SupplierDao supplierDao = new SupplierDaoSqlite();

    private static final String INSERT ="INSERT INTO products (name, description, price, category_id, supplier_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        Product product = null;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE id = " + id);
            while(rs.next()){
                product = new Product(
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        productCategoryDao.find(rs.getInt("category_id")),
                        supplierDao.find(rs.getInt("supplier_id"))
                );
                product.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products");

            while (rs.next()){
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        "PLN",
                        rs.getString("description"),
                        category,
                        supplier
                        );
                products.add(product);
            }
            products = createProductList(rs);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE supplier_id = " + supplier.getId());
            products = createProductList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory)
    {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE category_id = " + productCategory.getId());
            products = createProductList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean insert(Product product) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, Double.valueOf(product.getDefaultPrice()));
            ps.setInt(4, product.getProductCategory().getId());
            ps.setInt(5, product.getSupplier().getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Product> getByFilters(String productName, String categoryId, String supplierId) {
        String sqlQury = "SELECT * FROM products";
        Map<Integer, String> params = new HashMap<>();
        List<Product> products = new ArrayList<>();
        Boolean isWhere = false;
        if(!productName.equals("")){
            sqlQury += " WHERE name LIKE '%"+productName+"%'";
            isWhere = true;
        }
        if(!categoryId.equals("all")){
            if(!isWhere) {
                sqlQury += " WHERE ";
                isWhere = true;
            }else{
                sqlQury += " AND ";
            }
            sqlQury += "category_id = ?";
            params.put(params.size()+1, categoryId);
        }
        if(!supplierId.equals("all")){
            if(!isWhere) {
                sqlQury += " WHERE ";
                isWhere = true;
            }else{
                sqlQury += " AND ";
            }
            sqlQury += "supplier_id = ?";
            params.put(params.size()+1, supplierId);
        }
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQury);
            for(Integer index : params.keySet()){
                preparedStatement.setString(index, params.get(index));
            }
            ResultSet rs = preparedStatement.executeQuery();
            products = createProductList(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    private List<Product> createProductList(ResultSet rs) throws SQLException{
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            Product product = new Product(
                    rs.getString("name"),
                    rs.getFloat("price"),
                    "PLN",
                    rs.getString("description"),
                    productCategoryDao.find(rs.getInt("category_id")),
                    supplierDao.find(rs.getInt("supplier_id"))
            );
            product.setId(rs.getInt("id"));
            products.add(product);
        }
        return products;
    }

}
