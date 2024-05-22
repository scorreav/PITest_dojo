package com.dojo.dojo_pitest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model(1L, 1L, "PICANTO", 2010);
    }

    @Test
    void getIdTest() {
        assertEquals(1L, model.getId());
    }

    @Test
    void getBrandIdTest() {
        assertEquals(1L, model.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("PICANTO", model.getName());
    }

    @Test
    void getYearTest() {
        assertEquals(2010, model.getYear());
    }

    @Test
    void setIdTest() {
        var newId = 2L;

        model.setId(newId);
        assertEquals(newId, model.getId());
    }

    @Test
    void setBrandIdTest() {
        var newBrandId = 2L;

        model.setBrandId(newBrandId);
        assertEquals(newBrandId, model.getBrandId());
    }

    @Test
    void setNameTest() {
        var newName = "SPORTAGE";

        model.setName(newName);
        assertEquals(newName, model.getName());
    }

    @Test
    void setYearTest() {
        var newYear = 2021;

        model.setYear(newYear);
        assertEquals(newYear, model.getYear());
    }
}