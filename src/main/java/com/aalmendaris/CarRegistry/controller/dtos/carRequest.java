package com.aalmendaris.CarRegistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class carRequest {
    private final @NonNull String brand;
    private final @NonNull String model;
    private final int Milleage;
    private final double Price;
    private final int Year;
    private final @NonNull String Description;
    private final @NonNull String Colour;
    private final @NonNull String FuelType;
    private final int NumDoors;
}
