package com.dojo.dojo_pitest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    private Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand(1L, "TOYOTA");
    }

    @Test
    void getIdTest() {
        assertEquals(1L, brand.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("TOYOTA", brand.getName());
    }

    @Test
    void setIdTest() {
        long newId = 2L;

        brand.setId(newId);
        assertEquals(newId, brand.getId());
    }

    @Test
    void setNameTest() {
        String newName = "KIA";

        brand.setName(newName);
        assertEquals(newName, brand.getName());
    }
}