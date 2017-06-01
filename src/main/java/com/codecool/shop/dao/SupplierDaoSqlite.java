package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite extends BaseDao implements SupplierDao {

    public SupplierDaoSqlite(Connection connection) {
        super(connection);
    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM suppliers WHERE id = " + Integer.toString(id));
            if(rs.next()){
                supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
                rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM suppliers");
            while(rs.next()){
                Supplier supplier = new Supplier(
                        rs.getString("name"),
                        rs.getString("description")
                );
                supplier.setId(rs.getInt("id"));
                suppliers.add(supplier);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
