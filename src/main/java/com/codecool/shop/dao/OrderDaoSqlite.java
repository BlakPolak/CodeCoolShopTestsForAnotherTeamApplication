package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pati on 18.05.17.
 */
public class OrderDaoSqlite extends BaseDao implements OrderDao{

    private final String SELECTALL = "SELECT * FROM orders, users WHERE orders.user_id==users.id";

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
                System.out.println(id);

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
    public List<Order> getAll() {

        List<Order> orders = new ArrayList<>();
//        ProductCategoryDao category = new ProductCategoryDaoSqlite();
//        SupplierDao supplier = new SupplierDaoSqlite();

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
        List<Order> orders = null;

        while (rs.next()){
            Order order = new Order(rs.getInt("id"), rs.getInt("user_id"));
            order.setPaid(rs.getBoolean("paid"));
            order.setSend(rs.getBoolean("send"));
            order.setUser(new User(rs.getString("firstName"),
                                   rs.getString("lastName"),
                                   rs.getString("adres"),
                                   rs.getString("phone"),
                                   rs.getString("email")
            ));

            orders.add(order);
        }

        return orders;
    }

}
