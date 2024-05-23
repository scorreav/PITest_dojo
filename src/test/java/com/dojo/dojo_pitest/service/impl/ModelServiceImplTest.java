package com.dojo.dojo_pitest.service.impl;

import com.dojo.dojo_pitest.entity.ModelEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Model;
import com.dojo.dojo_pitest.model.dto.CreateModelDTO;
import com.dojo.dojo_pitest.model.dto.UpdateModelDTO;
import com.dojo.dojo_pitest.repository.ModelRepository;
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

class ModelServiceImplTest {

    @InjectMocks
    private ModelServiceImpl modelService;

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private IMapper iMapper;

    private ModelEntity modelEntity;

    private Model model;

    private CreateModelDTO modelDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        modelService = new ModelServiceImpl(modelRepository, iMapper);
        modelEntity = new ModelEntity(1L, 1L, "ONIX", 2022);
        model = new Model(1L, 1L, "ONIX", 2022);
        modelDTO = new CreateModelDTO(1L, "onix", 2010);
    }

    @AfterEach
    void tearDown() {
        modelService = null;
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
    void createAlreadyModelTest() {

    }

    @Test
    void updateTest() {

    }
}