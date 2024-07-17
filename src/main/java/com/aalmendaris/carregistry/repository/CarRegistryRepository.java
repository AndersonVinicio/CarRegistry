package com.aalmendaris.carregistry.repository;

import com.aalmendaris.carregistry.repository.entitys.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRegistryRepository extends JpaRepository<CarEntity, Integer> {
    //void addCarRepository(CarEntity carEntity);
    //Car foundCarRepository(int id);
    //Car updateCarRepository(int id, Car car);
    //Boolean DeleteCarRepository(int id);

}
