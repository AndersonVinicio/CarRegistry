package com.aalmendaris.CarRegistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
}
