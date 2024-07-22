package com.aalmendaris.carregistry.service.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Car {
    private int id;
    @CsvBindByName(column = "BRAND")
    private String brand;
    @CsvBindByName(column = "MODEL")
    private String model;
    @CsvBindByName(column = "MILLEAGE")
    private int Milleage;
    @CsvBindByName(column = "PRICE")
    private double Price;
    @CsvBindByName(column = "YEAR")
    private int Year;
    @CsvBindByName(column = "DESCRIPTION")
    private String Description;
    @CsvBindByName(column = "COLOUR")
    private String Colour;
    @CsvBindByName(column = "FUELTYPE")
    private String FuelType;
    @CsvBindByName(column = "NUMDOORS")
    private int NumDoors;





}
