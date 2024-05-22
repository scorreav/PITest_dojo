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
        Mockito.when(brandRepository.findById(anyLong()))
                .thenReturn(Optional.of(brandEntity));
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(brand);

        Brand brandRta = brandService.get(1L);
        assertNotNull(brandRta);
        assertEquals(brandRta, brand);
    }

    @Test
    void getEmptyTest() {
        Mockito.when(brandRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(brand);

        assertThrows(ApplicationException.class, () -> brandService.get(1L));
    }

    @Test
    void getAllTest() {
        Mockito.when(brandRepository.findAll())
                .thenReturn(List.of(brandEntity));
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(brand);

        List<Brand> brands = brandService.getAll();
        assertFalse(brands.isEmpty());
        assertEquals(1, brands.size());
    }

    @Test
    void deleteTest() {
        Mockito.when(brandRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito.doNothing().when(brandRepository).deleteById(anyLong());

        assertTrue(brandService.delete(1L));
        verify(brandRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteNotExistTest() {
        Mockito.when(brandRepository.existsById(anyLong()))
                .thenReturn(false);

        assertThrows(ApplicationException.class, () -> brandService.delete(1L));
    }

    @Test
    void createTest() {
        Mockito.when(brandRepository.findByName(anyString()))
                .thenReturn(null);
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(brand);
        Mockito.when(brandRepository.save(any(BrandEntity.class)))
                .thenReturn(brandEntity);

        String brandName = "kia";
        Brand brandCreated = brandService.create(brandName);

        assertNotNull(brandCreated);
        assertEquals(brandName.toUpperCase(), brandCreated.getName());
        verify(iMapper, times(1)).brandEntityToBrand(any(BrandEntity.class));
    }

    @Test
    void createAlreadyBrandTest() {
        Mockito.when(brandRepository.findByName(anyString()))
                .thenReturn(brandEntity);

        assertThrows(ApplicationException.class, () -> brandService.create(brandName));
    }

    @Test
    void updateTest() {
        var brandDb = new BrandEntity(1L, "KIA");
        var newBrandEntity = new BrandEntity(1L, "SUBARU");
        var newBrand = new Brand(1L, "SUBARU");

        Mockito.when(brandRepository.findById(anyLong()))
                .thenReturn(Optional.of(brandDb));
        Mockito.when(brandRepository.save(any(BrandEntity.class)))
                .thenReturn(newBrandEntity);
        Mockito.when(iMapper.brandToBrandEntity(any(Brand.class)))
                .thenReturn(newBrandEntity);
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(newBrand);

        var brandUpdated = brandService.update(1L, "SUBARU");

        assertEquals(newBrand.getId(), brandUpdated.getId());
        assertNotEquals(brandDb.getName(), brandUpdated.getName());
        verify(brandRepository, times(1)).save(any(BrandEntity.class));
    }

    @Test
    void updateEmptyValueTest() {
        var brandDb = new BrandEntity(1L, "KIA");
        var newBrandEntity = new BrandEntity(1L, "");
        var newBrand = new Brand(1L, "");

        Mockito.when(brandRepository.findById(anyLong()))
                .thenReturn(Optional.of(brandDb));
        Mockito.when(brandRepository.save(any(BrandEntity.class)))
                .thenReturn(newBrandEntity);
        Mockito.when(iMapper.brandToBrandEntity(any(Brand.class)))
                .thenReturn(newBrandEntity);
        Mockito.when(iMapper.brandEntityToBrand(any(BrandEntity.class)))
                .thenReturn(newBrand);

        var brandUpdated = brandService.update(1L, "");

        assertEquals(newBrand.getId(), brandUpdated.getId());
        assertEquals("", brandUpdated.getName());
    }

    @Test
    void updateValueExceptionTest() {
        Mockito.when(brandRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> brandService.update(1L, "KIA"));
    }
}