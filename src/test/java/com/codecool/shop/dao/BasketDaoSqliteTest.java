package com.codecool.shop.dao;

import com.codecool.shop.model.*;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class BasketDaoSqliteTest {

    Connection connection;
    BasketDaoSqlite basketDaoSqlite;


    @BeforeEach
    public void setUp() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BasketDaoSqliteTest.sql");
        basketDaoSqlite = new BasketDaoSqlite(connection);

    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }


}