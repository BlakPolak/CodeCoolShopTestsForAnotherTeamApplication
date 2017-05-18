package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDaoSqlite extends BaseDao implements UserDao {


    @Override
    public Integer add(User user) {
        Integer id = null;
        try {
            String query = "INSERT INTO users (first_name, last_name, adres, phone, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getAdres());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
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
                    prepareStatement("SELECT id FROM users WHERE email = ?");
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

}
