package com.aalmendaris.CarRegistry.service;

import com.aalmendaris.CarRegistry.repository.BrandRepository;
import com.aalmendaris.CarRegistry.repository.CarRegistryRepository;
import com.aalmendaris.CarRegistry.repository.entitys.BrandEntity;
import com.aalmendaris.CarRegistry.repository.entitys.CarEntity;
import com.aalmendaris.CarRegistry.service.impl.CarRegistryServiceimpl;
import com.aalmendaris.CarRegistry.service.mappers.CarMapperEntity;
import com.aalmendaris.CarRegistry.service.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarRegistryServiceImplTest {
    @InjectMocks
    private CarRegistryServiceimpl carRegistryServiceimpl;

    @Mock
    private CarRegistryRepository carRegistryRepository;

    @Mock
    private BrandRepository brandRepository;

    @Test
    void test_founCarService(){
        int testCarEntity_ID = 2;
        int testBrandEntity_id= 2;
        CarEntity testCarEntity = new CarEntity();
        testCarEntity.setId(testCarEntity_ID);

        BrandEntity testBrandEntity = new BrandEntity();
        testBrandEntity.setIdBrand(testBrandEntity_id);
        testBrandEntity.setName("Ford");
        testBrandEntity.setWarranty(5);
        testBrandEntity.setCountry("EEUU");

        testCarEntity.setBrand(testBrandEntity);
        testCarEntity.setModel("fiesta");
        testCarEntity.setMilleage(0);
        testCarEntity.setPrice(20000);
        testCarEntity.setYear(2020);
        testCarEntity.setDescription("turismo todo multimedia incliudo");
        testCarEntity.setColour("negro");
        testCarEntity.setFuelType("gasolina");
        testCarEntity.setNumDoors(5);

        Car test_Car = new Car(
                testCarEntity.getId(),
                testCarEntity.getBrand().getName(),
                testCarEntity.getModel(),
                testCarEntity.getMilleage(),
                testCarEntity.getPrice(),
                testCarEntity.getYear(),
                testCarEntity.getDescription(),
                testCarEntity.getColour(),
                testCarEntity.getFuelType(),
                testCarEntity.getNumDoors()
        );

        when(carRegistryRepository.findById(testCarEntity_ID)).thenReturn(Optional.of(testCarEntity));

        Car result = carRegistryServiceimpl.foundCarService(testCarEntity_ID);

        assertNotNull(result);
        assertEquals(test_Car.getId(),result.getId());
        assertEquals(test_Car.getBrand(),result.getBrand());
        assertEquals(test_Car.getModel(),result.getModel());
        assertEquals(test_Car.getMilleage(),result.getMilleage());
        assertEquals(test_Car.getPrice(),result.getPrice());
        assertEquals(test_Car.getYear(),result.getYear());
        assertEquals(test_Car.getDescription(),result.getDescription());
        assertEquals(test_Car.getColour(),result.getColour());
        assertEquals(test_Car.getFuelType(),result.getFuelType());
        assertEquals(test_Car.getNumDoors(),result.getNumDoors());

        verify(carRegistryRepository).findById(testCarEntity_ID);
    }

    @Test
    void test_addCarService(){
        Car test_Car = Car.builder()
                .brand("Dacia")
                .model("sandero")
                .Milleage(0)
                .Price(13000)
                .Year(2013)
                .Description("turismo de concesionario")
                .Colour("blanco")
                .FuelType("diesel")
                .NumDoors(5)
                .build();

        BrandEntity testBrandEntity = BrandEntity.builder()
                .idBrand(1)
                .name("Dacia")
                .warranty(5)
                .country("Rumania")
                .build();

        CarEntity Test_CarEntity = CarMapperEntity.carToCarEntity(test_Car,testBrandEntity);


        when(brandRepository.findByName(test_Car.getBrand())).thenReturn(testBrandEntity);
        when(carRegistryRepository.save(any(CarEntity.class))).thenReturn(Test_CarEntity);

        carRegistryServiceimpl.addCarService(test_Car);

        verify(brandRepository).findByName(test_Car.getBrand());
        verify(carRegistryRepository).save(any(CarEntity.class));
    }
}
