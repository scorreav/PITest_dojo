package com.dojo.dojo_pitest.mapper.impl;

import com.dojo.dojo_pitest.entity.BrandEntity;
import com.dojo.dojo_pitest.entity.ModelEntity;
import com.dojo.dojo_pitest.exception.ApplicationException;
import com.dojo.dojo_pitest.mapper.IMapper;
import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.model.Model;

public class MapperImpl implements IMapper {

    @Override
    public Brand brandEntityToBrand(BrandEntity brandEntity) {
        verifyParameter(brandEntity);
        return new Brand(brandEntity.getId(), brandEntity.getName());
    }

    @Override
    public BrandEntity brandToBrandEntity(Brand brand) {
        verifyParameter(brand);
        return new BrandEntity(brand.getId(), brand.getName());
    }

    @Override
    public Model modelEntityToModel(ModelEntity modelEntity) {
        verifyParameter(modelEntity);
        return new Model(modelEntity.getId(), modelEntity.getBrandId(), modelEntity.getName(), modelEntity.getYear());
    }

    @Override
    public ModelEntity modelToModelEntity(Model model) {
        verifyParameter(model);
        return new ModelEntity(model.getId(), model.getBrandId(), model.getName(), model.getYear());
    }

    private void verifyParameter(Object object) {
        if(object == null)
            throw new ApplicationException("No es posible realizar el mapeo, clase nula");
    }
}
