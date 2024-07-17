package com.aalmendaris.carregistry.service;

import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.model.UserLoginAndSignUp;

public interface UserService {
    UserEntity singUp(UserLoginAndSignUp userLoginAndSignUp, String passworEncoding, String role);
}
