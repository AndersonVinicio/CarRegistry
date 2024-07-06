package com.aalmendaris.CarRegistry.controller.mappers;

import com.aalmendaris.CarRegistry.controller.dtos.UserResponseDto;
import com.aalmendaris.CarRegistry.controller.dtos.UserRequestDto;
import com.aalmendaris.CarRegistry.service.model.User;

public class UsersToUsersDtoMapper {
    public static User UserDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .surname(userRequestDto.getSurname())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
    }
    public static UserResponseDto UserToUsersDto(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }
}
