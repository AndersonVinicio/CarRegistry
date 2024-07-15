package com.aalmendaris.CarRegistry.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class Car {
    private int id;
    private String brand;
    private String model;
    private int Milleage;
    private double Price;
    private int Year;
    private String Description;
    private String Colour;
    private String FuelType;
    private int NumDoors;

}
