package com.dojo.dojo_pitest.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateModelDTO extends CreateModelDTO {

    private long id;

    public UpdateModelDTO (long id, long brandId, String name, int year) {
        super(brandId, name, year);
        this.id = id;
    }
}
