package com.aalmendaris.CarRegistry.service.mappers;

import com.aalmendaris.CarRegistry.repository.entitys.BrandEntity;
import com.aalmendaris.CarRegistry.repository.entitys.CarEntity;
import com.aalmendaris.CarRegistry.service.model.Car;

public class CarMapperEntity {
    public static CarEntity carToCarEntity(Car car, BrandEntity brandEntity){
        return CarEntity.builder()
                .id(car.getId())
                .brand(brandEntity)
                .model(car.getModel())
                .milleage(car.getMilleage())
                .price(car.getPrice())
                .year(car.getYear())
                .description(car.getDescription())
                .colour(car.getColour())
                .fuelType(car.getFuelType())
                .numDoors(car.getNumDoors()).build();
    }

    public static Car carEntityToCar(CarEntity carEntity){
        return new Car(
                carEntity.getId(),
                carEntity.getBrand().getName(),
                carEntity.getModel(),
                carEntity.getMilleage(),
                carEntity.getPrice(),
                carEntity.getYear(),
                carEntity.getDescription(),
                carEntity.getColour(),
                carEntity.getFuelType(),
                carEntity.getNumDoors()
        );
    }
}
