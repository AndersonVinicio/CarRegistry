package com.aalmendaris.carregistry.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingUpRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}
