package com.aalmendaris.carregistry.service.impl;

import com.aalmendaris.carregistry.repository.UserRepository;
import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.UserService;
import com.aalmendaris.carregistry.service.model.UserLoginAndSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.aalmendaris.carregistry.service.mappers.UserLoginSignUpToUserEntityMapper.userLoginAndSignUpToUserEntity;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String email) {
                return userRepository.findByEmail(email)
                        .orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }


    @Override
    public UserEntity singUp(UserLoginAndSignUp userLoginAndSignUp, String passworEncoding, String role) {
        return userRepository.save(userLoginAndSignUpToUserEntity(userLoginAndSignUp,passworEncoding,role));
    }
}
