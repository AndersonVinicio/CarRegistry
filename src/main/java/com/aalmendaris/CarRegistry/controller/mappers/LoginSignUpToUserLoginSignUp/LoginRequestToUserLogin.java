package com.aalmendaris.CarRegistry.controller.mappers.LoginSignUpToUserLoginSignUp;

import com.aalmendaris.CarRegistry.controller.dtos.LoginRequest;
import com.aalmendaris.CarRegistry.service.model.UserLogin;

public class LoginRequestToUserLogin {
    public static UserLogin uSerLoginToLoginRequest(LoginRequest loginRequest){
        return UserLogin
                .builder()
                .email(loginRequest.getEmail())
                .password(loginRequest.getPassword())
                .build();
    }
}
