package com.dojo.dojo_pitest.mapper.impl;

import com.dojo.dojo_pitest.entity.BrandEntity;
import com.dojo.dojo_pitest.entity.ModelEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.model.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperImplTest {

    private IMapper iMapper;

    private BrandEntity brandEntity;

    private Brand brand;

    private ModelEntity modelEntity;

    private Model model;

    @BeforeEach
    void setUp() {
        iMapper = new MapperImpl();

        brandEntity = new BrandEntity(1L, "CHEVROLET");
        brand = new Brand(1L, "CHEVROLET");

        modelEntity = new ModelEntity(1L, 1L, "AVEO", 2000);
        model = new Model(1L, 1L, "AVEO", 2000);
    }

    @AfterEach
    void tearDown() {
        iMapper = null;

        brandEntity = null;
        brand = null;
        modelEntity = null;
        model = null;
    }

    @Test
    void brandEntityToBrandTest() {
        var brandMapper = iMapper.brandEntityToBrand(brandEntity);

        assertNotNull(brandMapper);
        assertAll(
                () -> assertEquals(brand.getId(), brandMapper.getId()),
                () -> assertEquals(brand.getName(), brandMapper.getName())
        );
    }

    @Test
    void brandEntityToBrandExceptionTest() {
        assertThrows(ApplicationException.class, () -> iMapper.brandEntityToBrand(null));
    }

    @Test
    void brandToBrandEntityTest() {
        var brandEntityMapper = iMapper.brandToBrandEntity(brand);

        assertNotNull(brandEntityMapper);
        assertAll(
                () -> assertEquals(brandEntity.getId(), brandEntityMapper.getId()),
                () -> assertEquals(brandEntity.getName(), brandEntityMapper.getName())
        );
    }

    @Test
    void brandToBrandEntityExceptionTest() {
        assertThrows(ApplicationException.class, () -> iMapper.brandToBrandEntity(null));
    }

    @Test
    void modelEntityToModelTest() {
        var modelMapper = iMapper.modelEntityToModel(modelEntity);

        assertNotNull(modelMapper);
        assertAll(
                () -> assertEquals(model.getId(), modelMapper.getId()),
                () -> assertEquals(model.getBrandId(), modelMapper.getBrandId()),
                () -> assertEquals(model.getName(), modelMapper.getName()),
                () -> assertEquals(model.getYear(), modelMapper.getYear())
        );
    }

    @Test
    void modelEntityToModelExceptionTest() {
        assertThrows(ApplicationException.class, () -> iMapper.modelEntityToModel(null));
    }

    @Test
    void modelToModelEntityTest() {
        var modelEntityMapper = iMapper.modelToModelEntity(model);

        assertNotNull(modelEntityMapper);
        assertAll(
                () -> assertEquals(modelEntity.getId(), modelEntityMapper.getId()),
                () -> assertEquals(modelEntity.getBrandId(), modelEntityMapper.getBrandId()),
                () -> assertEquals(modelEntity.getName(), modelEntityMapper.getName()),
                () -> assertEquals(modelEntity.getYear(), modelEntityMapper.getYear())
        );
    }

    @Test
    void modelToModelEntityExceptionTest() {
        assertThrows(ApplicationException.class, () -> iMapper.modelToModelEntity(null));
    }
}