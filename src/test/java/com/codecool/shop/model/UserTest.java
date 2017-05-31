package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {

        private User user;

        @BeforeEach
        void setup() {
            user = new User("test", "test", "test", "test", "test");
        }

        @Test
        void testSetAndGetId() {
            user.setId(-1);
            assertEquals(-1, user.getId(), 0.0000001);
        }

        @Test
        void ifIdLTZeroThrowsIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                user.setId(-1);
            });
        }
    }