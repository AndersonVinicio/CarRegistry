package com.aalmendaris.CarRegistry.service.impl;

import com.aalmendaris.CarRegistry.controller.dtos.JwtResponse;
import com.aalmendaris.CarRegistry.controller.dtos.LoginRequest;
import com.aalmendaris.CarRegistry.controller.dtos.SingUpRequest;
import com.aalmendaris.CarRegistry.repository.UserRepository;
import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import com.aalmendaris.CarRegistry.service.model.UserLoginAndSignUp;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserServiceimpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserLoginAndSignUp signup(UserLoginAndSignUp request) throws BadRequestException {
        var jwt = jwtService.generateToken(userService.singUp(request, passwordEncoder.encode(request.getPassword()),"ROLE_CLIENT"));
        return UserLoginAndSignUp.builder().token(jwt).build();
    }

    public UserLoginAndSignUp sigUpVendor(UserLoginAndSignUp request) throws BadRequestException{
        var jwt = jwtService.generateToken(userService.singUp(request, passwordEncoder.encode(request.getPassword()), "ROLE_VENDOR"));
        return UserLoginAndSignUp.builder().token(jwt).build();
    }

    public UserLoginAndSignUp login(UserLoginAndSignUp request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid user or password"));

        var jwt = jwtService.generateToken(user);
        return UserLoginAndSignUp.builder().token(jwt).build();
    }
}
