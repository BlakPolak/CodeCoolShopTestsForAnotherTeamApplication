package com.codecool.shop.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;


class BaseModelTest {
    @Test
    void testForGetIdWithoutSettingIdInConstructor() {
        BaseModel baseModel = new BaseModel("name");
        assertEquals(null, baseModel.getId());
    }
}