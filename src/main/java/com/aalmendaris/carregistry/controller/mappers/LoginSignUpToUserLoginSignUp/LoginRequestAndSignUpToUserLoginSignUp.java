package com.aalmendaris.carregistry.controller.mappers.LoginSignUpToUserLoginSignUp;

import com.aalmendaris.carregistry.controller.dtos.JwtResponse;
import com.aalmendaris.carregistry.controller.dtos.LoginRequest;
import com.aalmendaris.carregistry.controller.dtos.SingUpRequest;
import com.aalmendaris.carregistry.service.model.UserLoginAndSignUp;

public class LoginRequestAndSignUpToUserLoginSignUp {
    public static UserLoginAndSignUp userLoginMapper(LoginRequest loginRequest){
        return UserLoginAndSignUp
                .builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }

    public static UserLoginAndSignUp userSignUpMapper(SingUpRequest singUpRequest){
        return UserLoginAndSignUp
                .builder()
                .name(singUpRequest.getName())
                .surname(singUpRequest.getSurname())
                .email(singUpRequest.getEmail())
                .password(singUpRequest.getPassword())
                .build();
    }

    public static JwtResponse returnToken(UserLoginAndSignUp userLoginAndSignUp){
        return JwtResponse.builder().token(userLoginAndSignUp.getToken()).build();
    }


}
