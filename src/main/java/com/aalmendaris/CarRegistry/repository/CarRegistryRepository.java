package com.aalmendaris.CarRegistry.repository;

import com.aalmendaris.CarRegistry.repository.entitys.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRegistryRepository extends JpaRepository<CarEntity, Integer> {
    //void addCarRepository(CarEntity carEntity);
    //Car foundCarRepository(int id);
    //Car updateCarRepository(int id, Car car);
    //Boolean DeleteCarRepository(int id);

}
