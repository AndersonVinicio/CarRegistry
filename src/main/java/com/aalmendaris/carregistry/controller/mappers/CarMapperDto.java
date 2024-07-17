package com.aalmendaris.carregistry.controller.mappers;

import com.aalmendaris.carregistry.controller.dtos.carRequest;
import com.aalmendaris.carregistry.controller.dtos.carResponse;
import com.aalmendaris.carregistry.service.model.Car;

public class CarMapperDto {
    public static Car dtoToCarMapping(carRequest dto){
        Car newCar = Car.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .Milleage(dto.getMilleage())
                .Price(dto.getPrice())
                .Year(dto.getYear())
                .Description(dto.getDescription())
                .Colour(dto.getColour())
                .FuelType(dto.getFuelType())
                .NumDoors(dto.getNumDoors())
                .build();
        return newCar;
    }

    public static carResponse carTocarResponseDto(Car car){
        return new carResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getMilleage(),
                car.getPrice(),
                car.getYear(),
                car.getDescription(),
                car.getColour(),
                car.getFuelType(),
                car.getNumDoors());
    }
}
