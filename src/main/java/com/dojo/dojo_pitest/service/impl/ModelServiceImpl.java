package com.dojo.dojo_pitest.service.impl;

import com.dojo.dojo_pitest.entity.ModelEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Model;
import com.dojo.dojo_pitest.model.dto.CreateModelDTO;
import com.dojo.dojo_pitest.model.dto.UpdateModelDTO;
import com.dojo.dojo_pitest.repository.ModelRepository;
import com.dojo.dojo_pitest.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final IMapper iMapper;

    @Override
    public Model get(long id) {
        Optional<ModelEntity> modelEntity = modelRepository.findById(id);

        if (modelEntity.isEmpty())
            throw new ApplicationException("No existe modelo con el id " + id);

        return iMapper.modelEntityToModel(modelEntity.get());
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.findAll()
                .stream()
                .flatMap(modelEntity -> Stream.of(iMapper.modelEntityToModel(modelEntity)))
                .toList();
    }

    @Override
    public boolean delete(long id) {
        boolean existId = modelRepository.existsById(id);

        if (!existId)
            throw new ApplicationException("No existe registro a eliminar con id " + id);

        modelRepository.deleteById(id);
        return true;
    }

    @Override
    public Model create(CreateModelDTO modelDTO) {
        var existModel = modelRepository.findByBrandIdAndName(modelDTO.getBrandId(), modelDTO.getName());

        if (existModel != null)
            throw new ApplicationException("Ya existe un modelo registrado " + modelDTO.getName());

        return iMapper.modelEntityToModel(
                modelRepository.save(
                        ModelEntity
                                .builder()
                                .brandId(modelDTO.getBrandId())
                                .name(modelDTO.getName())
                                .year(modelDTO.getYear())
                                .build()
                )
        );
    }

    @Override
    public Model update(UpdateModelDTO updateModelDTO) {
        var model = this.get(updateModelDTO.getId());

        model.setBrandId(updateModelDTO.getBrandId());
        model.setName(updateModelDTO.getName());
        model.setYear(updateModelDTO.getYear());

        return iMapper.modelEntityToModel(modelRepository.save(iMapper.modelToModelEntity(model)));
    }
}
