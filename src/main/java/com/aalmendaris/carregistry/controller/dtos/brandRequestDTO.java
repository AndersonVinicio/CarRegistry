package com.aalmendaris.carregistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class brandRequestDTO {
    @NonNull
    private final String name;
    private final int warranty;
    @NonNull
    private final String country;
}
