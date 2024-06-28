package com.aalmendaris.CarRegistry.controller.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class brandResponseDTO {
    private  final int id;
    @NonNull
    private final String name;
    private final int warranty;
    @NonNull
    private final String country;
}
