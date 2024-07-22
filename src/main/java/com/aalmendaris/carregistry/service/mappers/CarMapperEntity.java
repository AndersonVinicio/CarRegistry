package com.aalmendaris.carregistry.service.mappers;

import com.aalmendaris.carregistry.repository.entitys.BrandEntity;
import com.aalmendaris.carregistry.repository.entitys.CarEntity;
import com.aalmendaris.carregistry.service.model.Car;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Car> carEntityToCarList(List<CarEntity>carEntityList){
        List<Car>carList = new ArrayList<>();
        for (CarEntity carEntity : carEntityList){
            Car car = new Car(
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
            carList.add(car);
        }
        return carList;
    }

}
