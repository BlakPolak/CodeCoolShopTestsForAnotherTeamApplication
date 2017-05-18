package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BasketDaoSqlite extends BaseDao implements BasketDao{

    private final String SELECTALL = "SELECT * FROM basket, products WHERE basket.product_id = products.id";
    private final String FINDID = "SELECT * FROM basket, products WHERE basket.product_id = products.id AND order_id=?";

    @Override
    public void add(Basket basket, Integer orderId) {
        for (BasketItem item: basket.getItems()) {
            try {
                String query = "INSERT INTO baskets (order_id, product_id, quantity) VALUES (?, ?, ?)";
                PreparedStatement statement = this.getConnection().prepareStatement(query);
                statement.setInt(1, orderId);
                statement.setInt(2, item.getProduct().getId());
                statement.setInt(3, item.getQuantity());
                statement.executeUpdate();

                statement.close();


            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Basket find(int id) {

        Connection conn = this.getConnection();

        Basket basket = null;
        try {
            PreparedStatement ps = conn.prepareStatement(FINDID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            //basket = new Basket();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return basket;

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Basket> getAll() {
        return null;
    }
}
