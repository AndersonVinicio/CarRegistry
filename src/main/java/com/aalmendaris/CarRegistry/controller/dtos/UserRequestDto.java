package com.aalmendaris.CarRegistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class UserRequestDto {
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
}
