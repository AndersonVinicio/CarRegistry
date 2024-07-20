package com.aalmendaris.carregistry.controller.mappers;

import com.aalmendaris.carregistry.controller.dtos.UserResponseDto;
import com.aalmendaris.carregistry.controller.dtos.UserRequestDto;
import com.aalmendaris.carregistry.service.model.User;

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
