package com.dojo.dojo_pitest.service.impl;

import com.dojo.dojo_pitest.entity.BrandEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private IMapper iMapper;

    private BrandEntity brandEntity;

    private Brand brand;

    private String brandName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        brandService = new BrandServiceImpl(brandRepository, iMapper);
        brandName = "KIA";
        brandEntity = new BrandEntity(1L, brandName);
        brand = new Brand(1L, brandName);
    }

    @AfterEach
    void tearDown() {
        brandService = null;
    }

    @Test
    void getTest() {

    }

    @Test
    void getEmptyTest() {

    }

    @Test
    void getAllTest() {

    }

    @Test
    void deleteTest() {

    }

    @Test
    void deleteNotExistTest() {

    }

    @Test
    void createTest() {

    }

    @Test
    void createAlreadyBrandTest() {

    }

    @Test
    void updateTest() {

    }

    @Test
    void updateEmptyValueTest() {

    }

    @Test
    void updateValueExceptionTest() {

    }
}