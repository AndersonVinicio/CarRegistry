package com.aalmendaris.CarRegistry.service.mappers;


import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import com.aalmendaris.CarRegistry.service.model.UserLoginAndSignUp;
import org.springframework.security.crypto.password.PasswordEncoder;

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
