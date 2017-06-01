package com.codecool.shop.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class BaseDaoTest {
    Connection connection;

    @Test
    public void testGetConnectionFailsWhenConnectionIsNull() {
        BaseDao baseDao = new BaseDao(connection);
        Assertions.assertNotEquals(baseDao.getConnection(), null);
    }

}
