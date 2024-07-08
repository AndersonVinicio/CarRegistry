package com.aalmendaris.CarRegistry.service;

import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import com.aalmendaris.CarRegistry.service.model.User;

public interface UserService {
    UserEntity singUp(UserEntity userEntity);
}
