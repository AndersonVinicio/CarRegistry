package com.aalmendaris.CarRegistry.service.impl;

import com.aalmendaris.CarRegistry.controller.dtos.UserRequestDto;
import com.aalmendaris.CarRegistry.repository.UserRepository;
import com.aalmendaris.CarRegistry.service.UserService;
import com.aalmendaris.CarRegistry.service.mappers.UserToUserEntityMapper;
import com.aalmendaris.CarRegistry.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aalmendaris.CarRegistry.service.mappers.UserToUserEntityMapper.UserToUserEntity;

public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void singUp(User user) {
        userRepository.save(UserToUserEntity(user));
    }
}
