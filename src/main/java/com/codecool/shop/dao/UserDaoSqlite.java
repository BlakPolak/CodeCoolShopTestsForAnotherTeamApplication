package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by pati on 17.05.17.
 */
public class UserDaoSqlite extends BaseDao implements UserDao {

    @Override
    public void add(User user) {
        try {
            PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO users" +
                    "(first_name, last_name, adres, phone, email)" +
                    "VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAdres());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());

            statement.execute();

        } catch (SQLException e ) {
            e.printStackTrace();

        }

    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
