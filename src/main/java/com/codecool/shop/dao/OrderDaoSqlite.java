package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoSqlite extends BaseDao implements OrderDao{

    private final String SELECTALL = "SELECT user_id as userid, paid as paid, send as send, users.first_name as name, last_name as last, adres as address, phone as phone, email as email, orders.id as id FROM orders, users WHERE orders.user_id==users.id";
    private BasketDao basketDao;

    public OrderDaoSqlite(Connection connection) {
        super(connection);
        this.basketDao = new BasketDaoSqlite(connection);
    }

    @Override
    public Integer add(Order order) {
        Integer id = null;
        try {
            String query = "INSERT INTO orders (user_id, paid, send) VALUES (?, ?, ?)";
            PreparedStatement statement = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getUserId());
            statement.setBoolean(2, false);
            statement.setBoolean(3, false);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);

            }
            rs.close();
            statement.close();


        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public void updatePaid(Integer id) {
        if(id < 0) throw new IllegalArgumentException();
        try {
            String query = "UPDATE orders SET paid = ? WHERE id = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(query);
            statement.setBoolean(1, true);
            statement.setInt(2, id);

            statement.executeUpdate();
            statement.close();


        } catch (SQLException e ) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Order> getAll() {

        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECTALL);
            orders = createOrdersList(rs);

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> createOrdersList(ResultSet rs) throws SQLException {

        List<Order> orders = new ArrayList<>();

        while (rs.next()){

            Basket basket;
            Order order = new Order(rs.getInt("id"), rs.getInt("userid"));
            order.setPaid(rs.getBoolean("paid"));
            order.setSend(rs.getBoolean("send"));
            order.setUser(new User(rs.getString("name"),
                                   rs.getString("last"),
                                   rs.getString("address"),
                                   rs.getString("phone"),
                                   rs.getString("email")
            ));


            order.setBasket(basketDao.find(rs.getInt("id")));
            orders.add(order);
        }

        return orders;
    }

}
