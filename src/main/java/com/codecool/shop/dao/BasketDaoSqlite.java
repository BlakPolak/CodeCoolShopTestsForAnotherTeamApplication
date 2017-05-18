package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BasketDaoSqlite extends BaseDao implements BasketDao{

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
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Basket> getAll() {
        return null;
    }
}
