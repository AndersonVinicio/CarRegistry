package com.aalmendaris.carregistry.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginAndSignUp {
    public String name;
    public String surname;
    public String email;
    public String password;
    public String role;
    public String token;
}
