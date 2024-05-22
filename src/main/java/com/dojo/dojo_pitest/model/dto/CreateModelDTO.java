package com.dojo.dojo_pitest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelDTO {

    private long brandId;

    private String name;

    private int year;
}
