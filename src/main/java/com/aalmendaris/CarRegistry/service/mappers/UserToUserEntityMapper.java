package com.aalmendaris.CarRegistry.service.mappers;

import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import com.aalmendaris.CarRegistry.service.model.User;

public class UserToUserEntityMapper {
    public static User UserEntityToUser (UserEntity userEntity){
        return User.builder()
                .id(userEntity.getIdUser())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .build();
    }
    public static UserEntity UserToUserEntity (User user){
        return UserEntity.builder()
                .idUser(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
