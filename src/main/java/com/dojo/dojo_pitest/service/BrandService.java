package com.dojo.dojo_pitest.service;

import com.dojo.dojo_pitest.model.Brand;

public interface BrandService extends BasicService<Brand> {
    Brand create(String brandName);
    Brand update(long id, String brandName);
}
