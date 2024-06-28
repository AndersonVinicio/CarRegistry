package com.aalmendaris.CarRegistry.repository;

import com.aalmendaris.CarRegistry.repository.entitys.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity,Integer> {
    @Query(value = "SELECT * FROM brand WHERE name = ?1", nativeQuery = true)
    BrandEntity findByName(String name);
}
