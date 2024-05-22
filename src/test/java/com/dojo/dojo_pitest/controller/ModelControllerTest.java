package com.dojo.dojo_pitest.controller;

import com.dojo.dojo_pitest.model.Model;
import com.dojo.dojo_pitest.model.dto.CreateModelDTO;
import com.dojo.dojo_pitest.service.ModelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ModelControllerTest {

    @InjectMocks
    private ModelController modelController;

    @Mock
    private ModelService modelService;

    private Model model;

    @BeforeEach
    void setUp() {
        modelController = new ModelController(modelService);
        model = new Model(1L, 1L,"TRACKER", 2001);
    }

    @AfterEach
    void tearDown() {
        modelController = null;
    }

    @Test
    void getModelTest() {
        Mockito.when(modelService.get(anyLong()))
                .thenReturn(model);

        ResponseEntity<Model> responseEntity = modelController.getModel(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void getAllBrandsTest() {
        Mockito.when(modelService.getAll())
                .thenReturn(List.of(model));

        ResponseEntity<List<Model>> responseEntity = modelController.getAllModels();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void createTest() {
        Mockito.when(modelService.create(any(CreateModelDTO.class)))
                .thenReturn(model);

        ResponseEntity<Model> responseEntity = modelController.create(new CreateModelDTO(1L, "TRACKER", 2015));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }
}