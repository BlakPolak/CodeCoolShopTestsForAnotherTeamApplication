package com.codecool.shop.dao;

import com.codecool.shop.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import db.TestSqliteJDBCConnector;
import static org.junit.jupiter.api.Assertions.*;


public class UserDaoSqliteTest {

    private Connection connection;
    private UserDaoSqlite userDaoSqlite;

    @BeforeEach
    void setup() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        userDaoSqlite = new UserDaoSqlite(connection);
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/UserDaoSqlite.sql");
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void testIfAddReturnsCorrectId() {
        User user = new User("Boleslaw", "Chrobry", "kasztel 12", "8787878787", "bolek666@buziaczek.pl");
        Integer id = userDaoSqlite.add(user);
        assertEquals(3, (int)id);
    }
}