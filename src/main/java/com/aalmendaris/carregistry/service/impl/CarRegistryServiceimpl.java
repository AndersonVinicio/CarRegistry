package com.aalmendaris.carregistry.service.impl;

import com.aalmendaris.carregistry.repository.BrandRepository;
import com.aalmendaris.carregistry.repository.CarRegistryRepository;
import com.aalmendaris.carregistry.repository.entitys.CarEntity;
import com.aalmendaris.carregistry.service.CarRegistryService;
import com.aalmendaris.carregistry.service.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.aalmendaris.carregistry.service.mappers.CarMapperEntity.*;

@Service
@Slf4j
public class CarRegistryServiceimpl implements CarRegistryService {

    @Autowired
    private CarRegistryRepository carRegistryRepository;
    @Autowired
    private BrandRepository brandRepository;




    @Override
    public void addCarService(Car car) {
        log.info("Nombre de marca: "+car.getBrand());
        carRegistryRepository.save(carToCarEntity(car, brandRepository.findByName(car.getBrand())));
    }

    /*@Override
    public Car updateCarService(int id, Car car) {
        return null;
    }

    @Override
    public Boolean DeleteCarService(int id) {
        return null;
    }*/

    @Override
    public Car foundCarService(int id) {
        return carEntityToCar(carRegistryRepository.findById(id).get());
    }

    @Override
    public Car updateCarService(int id, Car car) {
        Optional<CarEntity>foundCar=carRegistryRepository.findById(id);

        if (foundCar.isPresent()){
            Car updateCar = carEntityToCar(foundCar.get());
            updateCar.setBrand(car.getBrand());
            updateCar.setModel(car.getModel());
            updateCar.setMilleage(car.getMilleage());
            updateCar.setPrice(car.getPrice());
            updateCar.setYear(car.getYear());
            updateCar.setDescription(car.getDescription());
            updateCar.setColour(car.getColour());
            updateCar.setFuelType(car.getFuelType());
            updateCar.setNumDoors(car.getNumDoors());
            return carEntityToCar(carRegistryRepository.save(carToCarEntity(updateCar,brandRepository.findByName(car.getBrand()))));
        }else {
            return carEntityToCar(foundCar.get());
        }
    }

    /**
     *
     * @param id
     * @return Boolean
     *
     * Este metodo se encarga de eliminar dentro de el llamamos al metodo de la capa datos
     *
     */
    @Override
    public Car DeleteCarService(int id) {
        Optional<CarEntity>foundCar=carRegistryRepository.findById(id);
        if (foundCar.isPresent()){
            carRegistryRepository.deleteById(id);
            return carEntityToCar(foundCar.get());
        }else {
            return carEntityToCar(foundCar.get());
        }
    }

    @Override
    @Async
    public CompletableFuture<List<Car>> allCarService() {
        List<Car>allCar = carEntityToCarList(carRegistryRepository.findAll());
        return CompletableFuture.completedFuture(allCar);
    }

}
