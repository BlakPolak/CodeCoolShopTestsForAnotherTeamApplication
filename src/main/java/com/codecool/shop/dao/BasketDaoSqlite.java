package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.BasketItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoSqlite extends BaseDao implements BasketDao{

    private ProductDao productDao = new ProductDaoSqlite(SqliteJDBCConnector.getConnection());

    private final String SELECTALL = "SELECT * FROM basket, products WHERE basket.product_id = products.id";
    private final String FINDID = "SELECT product_id as product_id, products.name as name, description as description, price as price, quantity as quantity FROM baskets, products WHERE baskets.product_id = products.id AND order_id=?";

    public BasketDaoSqlite(Connection connection) {
        super(connection);
    }

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

        Basket basket;

        try {
            Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(FINDID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            basket = createBasketList(rs);

            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return basket;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void getAll() {
    }

    @Override
    public Basket createBasketList(ResultSet rs) throws SQLException {

        List<BasketItem> basketItems = new ArrayList<>();
        Basket singleBasket = new Basket();
        while (rs.next()) {
            BasketItem basketItem = new BasketItem(this.productDao.find(rs.getInt("product_id")), rs.getInt("quantity"));
            basketItems.add(basketItem);
        }
        singleBasket.setItems(basketItems);

        return singleBasket;
    }
}
