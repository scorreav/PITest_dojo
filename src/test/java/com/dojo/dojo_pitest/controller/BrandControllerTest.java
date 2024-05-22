package com.dojo.dojo_pitest.controller;

import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.service.BrandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class BrandControllerTest {

    @InjectMocks
    private BrandController brandController;

    @Mock
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandController = new BrandController(brandService);
    }

    @AfterEach
    void tearDown() {
        brandController = null;
    }

    @Test
    void getBrandTest() {
        Mockito.when(brandService.get(anyLong()))
                .thenReturn(new Brand(1L, "TOYOTA"));

        ResponseEntity<Brand> responseEntity = brandController.getBrand(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void getAllBrandsTest() {
        Mockito.when(brandService.getAll())
                .thenReturn(List.of(new Brand(1L, "TOYOTA"), new Brand(2L, "KIA")));

        ResponseEntity<List<Brand>> responseEntity = brandController.getAllBrands();

        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(responseEntity.getBody())
        );

    }

    @Test
    void createTest() {
        Mockito.when(brandService.create(anyString()))
                .thenReturn(new Brand(1L, "TOYOTA"));

        ResponseEntity<Brand> responseEntity = brandController.create("TOYOTA");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void updateTest() {
        Mockito.when(brandService.update(anyLong(), anyString()))
                .thenReturn(new Brand(1L, "TOYOTA"));

        ResponseEntity<Brand> responseEntity = brandController.update(1L, "AUDI");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}