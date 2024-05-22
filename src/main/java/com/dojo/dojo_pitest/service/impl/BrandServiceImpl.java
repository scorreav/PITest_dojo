package com.dojo.dojo_pitest.service.impl;

import com.dojo.dojo_pitest.entity.BrandEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.repository.BrandRepository;
import com.dojo.dojo_pitest.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final IMapper iMapper;

    @Override
    public Brand get(long id) {

        Optional<BrandEntity> brandEntity = brandRepository.findById(id);

        if (brandEntity.isEmpty())
            throw new ApplicationException("No existe brand con el id " + id);

        return iMapper.brandEntityToBrand(brandEntity.get());
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll()
                .stream()
                .flatMap(brandEntity -> Stream.of(iMapper.brandEntityToBrand(brandEntity)))
                .toList();
    }

    @Override
    public boolean delete(long id) {
        boolean existId = brandRepository.existsById(id);

        if (!existId)
            throw new ApplicationException("No existe registro a eliminar con id " + id);

        brandRepository.deleteById(id);
        return true;
    }

    @Override
    public Brand create(String brandName) {
        var dbRegister = brandRepository.findByName(brandName);

        if (dbRegister != null)
            throw new ApplicationException("Ya existe un registro con el nombre " + brandName);

        return iMapper.brandEntityToBrand(brandRepository.save(BrandEntity.builder().name(brandName).build()));
    }

    @Override
    public Brand update(long id, String brandName) {
        var brand = this.get(id);

        return iMapper.brandEntityToBrand(brandRepository.save(new BrandEntity(brand.getId(), brandName.toUpperCase())));
    }
}
