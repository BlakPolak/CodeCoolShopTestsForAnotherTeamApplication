package com.codecool.shop.dao;

import db.TestSqliteJDBCConnector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class SqliteJDBCConnectorTest {
    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException, IOException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/db/test.db");
        TestSqliteJDBCConnector.runSql(connection, "src/test/java/db/scripts/BaseStructure.sql");
    }
    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }
    @Test
    public void testSetConnectionThrowsExceptionIfConnectionIsNull() throws SQLException {
        assertThrows(SQLException.class, ()-> SqliteJDBCConnector.setConnection(null));
    }

    @Test
    public void testGetConnectionReturnsNull() {
        assertEquals(null, SqliteJDBCConnector.getConnection());
    }

    @Test
    public void testCreateTablesThrowsExceptionIfConnectionIsNull() throws SQLException {
        assertThrows(SQLException.class, ()-> SqliteJDBCConnector.createTables());
    }

    @Test
    public void testDropTablesThrowsExceptionIfConnectionIsNull() throws SQLException {
        assertThrows(SQLException.class, ()-> SqliteJDBCConnector.dropTables());
    }
}
