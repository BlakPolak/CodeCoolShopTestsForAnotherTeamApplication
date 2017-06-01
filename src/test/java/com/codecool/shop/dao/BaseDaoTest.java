package com.codecool.shop.dao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.mockito.Mockito.mock;

class BaseDaoTest {
    Connection connection;

    @Test
    public void testGetConnectionReturnsExpectedConnection() {
        connection = mock(Connection.class);
        BaseDao baseDao = new BaseDao(connection);
        Assertions.assertSame(baseDao.getConnection(), connection);
    }

}
