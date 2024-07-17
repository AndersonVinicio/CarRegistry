package com.aalmendaris.carregistry.service.mappers;


import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.model.UserLoginAndSignUp;

public class UserLoginSignUpToUserEntityMapper {
    public static UserEntity userLoginAndSignUpToUserEntity(UserLoginAndSignUp userLoginAndSignUp, String passwordCodofic, String role){
        return UserEntity.builder()
                .name(userLoginAndSignUp.getName())
                .surname(userLoginAndSignUp.getSurname())
                .email(userLoginAndSignUp.getEmail())
                .password(passwordCodofic)
                .role(role).build();
    }
}
