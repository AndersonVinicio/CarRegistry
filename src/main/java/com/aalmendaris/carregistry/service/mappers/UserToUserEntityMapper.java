package com.aalmendaris.carregistry.service.mappers;

import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.model.User;

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
