package com.aalmendaris.CarRegistry.service;

import com.aalmendaris.CarRegistry.service.model.Car;

import java.util.Optional;

public interface CarRegistryService {
    void addCarService(Car car);
    Car foundCarService(int id);
    Car updateCarService(int id, Car car);
    Car DeleteCarService(int id);
}
