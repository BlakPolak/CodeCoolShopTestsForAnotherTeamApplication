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
    private static ProductCategoryDao productCategoryDao;
    private static SupplierDao supplierDao;

    private static final String INSERT ="INSERT INTO products (name, description, price, category_id, supplier_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE ="UPDATE products SET name=?, description=?, price=?, category_id=?, supplier_id=? WHERE id=?";
    private static final String DELETE ="DELETE FROM products WHERE id=?";

    public ProductDaoSqlite(Connection connection) {
        super(connection);
        this.productCategoryDao = new ProductCategoryDaoSqlite(connection);
        this.supplierDao = new SupplierDaoSqlite(connection);

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
                rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void remove(int id) {
        System.out.println(id);
        try {
            Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            products = createProductList(rs);
            rs.close();
            statement.close();
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
            rs.close();
            statement.close();
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
            rs.close();
            statement.close();
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

    @Override
    public boolean update(Product product) {
        try {
            Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, Double.valueOf(product.getDefaultPrice()));
            ps.setInt(4, product.getProductCategory().getId());
            ps.setInt(5, product.getSupplier().getId());
            ps.setInt(6, product.getId());
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
            rs.close();
            preparedStatement.close();
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
