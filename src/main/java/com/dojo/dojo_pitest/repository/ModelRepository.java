package com.dojo.dojo_pitest.repository;

import com.dojo.dojo_pitest.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

    ModelEntity findByBrandIdAndName(long brandId, String name);
}
