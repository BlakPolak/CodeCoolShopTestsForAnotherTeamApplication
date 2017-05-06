package com.codecool.shop.dao;


import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSqlite implements SupplierDao{
    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;
        try {
            Statement statement = SqliteJDBCConnector.connection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM suppliers WHERE id = " + Integer.toString(id));

            if (rs.next()) {
                supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        }catch (SQLException e) {
            System.out.println();System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
            }


        return supplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<Supplier>();

        try {
            Connection connection = SqliteJDBCConnector.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM suppliers");
            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println("Connect to DB failed");
            System.out.println(e.getMessage());
        }

        return suppliers;
    }
}
