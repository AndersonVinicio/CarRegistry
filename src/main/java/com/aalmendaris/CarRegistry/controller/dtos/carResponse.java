package com.aalmendaris.CarRegistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class carResponse {

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
