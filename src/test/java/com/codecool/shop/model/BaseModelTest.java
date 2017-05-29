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

    @Test
    void testForGetIdWhenIdInConstructor() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        assertEquals(1,baseModel.getId());
    }

    @Test
    void testForGetNameWhenNameInConstructor() {
        BaseModel baseModel = new BaseModel("name");
        assertEquals("name",baseModel.getName());
    }

    @Test
    void testSetAndGetId() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        baseModel.setId(-1);
        assertEquals(-1,baseModel.getId());
    }
    @Test
    void testSetAndGetName() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        baseModel.setName("expectedName");
        assertEquals("expectedName",baseModel.getName());
    }
    @Test
    void testSetAndGetDescription() {
        BaseModel baseModel = new BaseModel(1, "name", "description");
        baseModel.setDescription("expectedDescription");
        assertEquals("expectedDescription",baseModel.getDescription());
    }

}