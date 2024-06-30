package com.aalmendaris.CarRegistry.service;

import com.aalmendaris.CarRegistry.service.model.Car;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface CarRegistryService {
    void addCarService(Car car);
    Car foundCarService(int id);
    Car updateCarService(int id, Car car);
    Car DeleteCarService(int id);
    CompletableFuture<List<Car>> allCarService();
}
