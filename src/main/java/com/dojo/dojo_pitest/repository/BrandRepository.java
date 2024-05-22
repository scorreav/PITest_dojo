package com.dojo.dojo_pitest.repository;

import com.dojo.dojo_pitest.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    BrandEntity findByName(String name);
}
