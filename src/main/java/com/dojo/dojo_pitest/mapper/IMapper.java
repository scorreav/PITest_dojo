package com.dojo.dojo_pitest.mapper;

import com.dojo.dojo_pitest.entity.BrandEntity;
import com.dojo.dojo_pitest.entity.ModelEntity;
import com.dojo.dojo_pitest.model.Brand;
import com.dojo.dojo_pitest.model.Model;


public interface IMapper {

    Brand brandEntityToBrand(BrandEntity brandEntity);
    BrandEntity brandToBrandEntity(Brand brand);

    Model modelEntityToModel(ModelEntity modelEntity);
    ModelEntity modelToModelEntity(Model model);
}
