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
        Mockito.when(modelRepository.findById(anyLong()))
                .thenReturn(Optional.of(modelEntity));
        Mockito.when(iMapper.modelEntityToModel(any(ModelEntity.class)))
                .thenReturn(model);

        Model modelRta = modelService.get(1L);
        assertNotNull(modelRta);
        assertEquals(modelRta, model);
    }

    @Test
    void getEmptyTest() {
        Mockito.when(modelRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Mockito.when(iMapper.modelEntityToModel(any(ModelEntity.class)))
                .thenReturn(model);

        assertThrows(ApplicationException.class, () -> modelService.get(1L));
    }

    @Test
    void getAllTest() {
        Mockito.when(modelRepository.findAll())
                .thenReturn(List.of(modelEntity));
        Mockito.when(iMapper.modelEntityToModel(any(ModelEntity.class)))
                .thenReturn(model);

        List<Model> models = modelService.getAll();
        assertFalse(models.isEmpty());
        assertEquals(1, models.size());
    }

    @Test
    void deleteTest() {
        Mockito.when(modelRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito.doNothing().when(modelRepository).deleteById(anyLong());

        assertTrue(modelService.delete(1L));
        verify(modelRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteNotExistTest() {
        Mockito.when(modelRepository.existsById(anyLong()))
                .thenReturn(false);

        assertThrows(ApplicationException.class, () -> modelService.delete(1L));
    }

    @Test
    void createTest() {
        Mockito.when(modelRepository.findByBrandIdAndName(anyLong(), anyString()))
                .thenReturn(null);
        Mockito.when(iMapper.modelEntityToModel(any(ModelEntity.class)))
                .thenReturn(model);
        Mockito.when(modelRepository.save(any(ModelEntity.class)))
                .thenReturn(modelEntity);

        Model modelCreated = modelService.create(modelDTO);

        assertNotNull(modelCreated);
        assertEquals(modelDTO.getName().toUpperCase(), modelCreated.getName());
    }

    @Test
    void createAlreadyModelTest() {
        Mockito.when(modelRepository.findByBrandIdAndName(anyLong(), anyString()))
                .thenReturn(modelEntity);

        assertThrows(ApplicationException.class, () -> modelService.create(modelDTO));
    }

    @Test
    void updateTest() {
        var modelDb = new ModelEntity(1L, 1L, "ONIX", 2009);
        var newModelDb = new ModelEntity(1L, 2L, "AVEO", 2011);

        Mockito.when(modelRepository.findById(anyLong()))
                .thenReturn(Optional.of(modelDb));
        Mockito.when(modelRepository.save(any(ModelEntity.class)))
                .thenReturn(newModelDb);
        Mockito.when(iMapper.modelEntityToModel(any(ModelEntity.class)))
                .thenReturn(model);
        Mockito.when(iMapper.modelToModelEntity(any(Model.class)))
                .thenReturn(modelEntity);

        var dto = new UpdateModelDTO(1L, 2L, "AVEO", 2011);
        var modelUpdated = modelService.update(dto);

        assertAll(
                () -> assertEquals(modelDb.getId(), modelUpdated.getId()),
                () -> assertEquals(dto.getBrandId(), modelUpdated.getBrandId()),
                () -> assertEquals(dto.getName(), modelUpdated.getName()),
                () -> assertEquals(dto.getYear(), modelUpdated.getYear())
        );

        verify(modelRepository, times(1)).findById(anyLong());
        verify(modelRepository, times(1)).save(any(ModelEntity.class));
    }
}