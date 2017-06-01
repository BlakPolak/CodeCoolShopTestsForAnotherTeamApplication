package com.codecool.shop.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codecool.shop.model.Supplier;
import db.TestSqliteJDBCConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SupplierDaoSqliteTest {

    SupplierDaoSqlite supplierDaoSqlite;
    Supplier supplier;
    Connection connection;

    @BeforeEach
    void setup() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        supplierDaoSqlite = new SupplierDaoSqlite(connection);
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/SupplierDaoSqlite.sql");
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void testIfFindMethodFailWhenIdLTZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            supplierDaoSqlite.find(-1);
        });
    }

}