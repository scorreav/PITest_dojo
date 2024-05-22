package com.dojo.dojo_pitest.service;

import com.dojo.dojo_pitest.model.Model;
import com.dojo.dojo_pitest.model.dto.CreateModelDTO;
import com.dojo.dojo_pitest.model.dto.UpdateModelDTO;

public interface ModelService extends BasicService<Model> {

    Model create(CreateModelDTO modelDTO);
    Model update(UpdateModelDTO updateModelDTO);
}
