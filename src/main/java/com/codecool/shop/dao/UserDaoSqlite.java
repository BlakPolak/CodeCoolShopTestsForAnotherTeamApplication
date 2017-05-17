package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        User user = null;

        try {
            PreparedStatement statement = this.getConnection().
                    prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {

                user = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("adres"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return user;
    }

    public Integer findId(String email) {
        Integer userId = null;

        try {
            PreparedStatement statement = this.getConnection().
                    prepareStatement("SELECT id FROM events WHERE email = ?");
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
//
//    private List<User> getUser(PreparedStatement statement) throws SQLException {
//        List<User> users = new ArrayList<User>();
//        ResultSet rs = statement.executeQuery();
//        while(rs.next()) {
//
//                User user = new User(
//                        rs.getString("first_name"),
//                        rs.getString("last_name"),
//                        rs.getString("adres"),
//                        rs.getString("phone"),
//                        rs.getString("email")
//                        );
//                user.setId(rs.getInt("id"));
//                users.add(user);
//            }
//        return users;
//    }
}
