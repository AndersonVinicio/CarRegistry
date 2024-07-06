package com.aalmendaris.CarRegistry.controller;

import com.aalmendaris.CarRegistry.controller.dtos.UserRequestDto;
import com.aalmendaris.CarRegistry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.aalmendaris.CarRegistry.controller.mappers.UsersToUsersDtoMapper.UserDtoToUser;

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserRequestDto userRequestDto){
        userService.singUp(UserDtoToUser(userRequestDto));
    }
}
