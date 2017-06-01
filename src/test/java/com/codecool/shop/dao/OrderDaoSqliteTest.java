package com.codecool.shop.dao;
import com.codecool.shop.model.Order;
import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class OrderDaoSqliteTest {
    private OrderDaoSqlite orderDaoSq;
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        orderDaoSq = new OrderDaoSqlite(connection);
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void testAddFailsWhenProductIsNull() {
        assertThrows(NullPointerException.class, ()-> orderDaoSq.add(null));
    }

    @Test
    public void testUpdatePaidFailsWhenIdLT0() {
        assertThrows(IllegalArgumentException.class, ()-> orderDaoSq.updatePaid(-1));
    }

    @Test
    public void testGetAllReturnsExpectedList() {
        List<Order> orders = new ArrayList<>();
        assertEquals(orders, orderDaoSq.getAll());
    }

    @Test
    public void testCreateOrdersListFailsWhenRsIsNull() {
        assertThrows(NullPointerException.class, ()-> orderDaoSq.createOrdersList(null));
    }
}