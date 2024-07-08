package com.aalmendaris.CarRegistry.service;

import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import com.aalmendaris.CarRegistry.service.model.User;
import com.aalmendaris.CarRegistry.service.model.UserLoginAndSignUp;

public interface UserService {
    UserEntity singUp(UserLoginAndSignUp userLoginAndSignUp, String passworEncoding, String role);
}
