package com.dojo.dojo_pitest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Model {

    private long id;

    private long brandId;

    private String name;

    private int year;
}
