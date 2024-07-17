package com.aalmendaris.carregistry.controller;

import com.aalmendaris.carregistry.controller.dtos.JwtResponse;
import com.aalmendaris.carregistry.controller.dtos.LoginRequest;
import com.aalmendaris.carregistry.controller.dtos.SingUpRequest;
import com.aalmendaris.carregistry.service.UserService;
import com.aalmendaris.carregistry.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aalmendaris.carregistry.controller.mappers.LoginSignUpToUserLoginSignUp.LoginRequestAndSignUpToUserLoginSignUp.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private final AuthenticationService authenticationService;


    @PostMapping("/addUser")
    public ResponseEntity<JwtResponse> addUser(@RequestBody SingUpRequest singUpRequest){
        try{

            JwtResponse jwtResponse = returnToken(authenticationService.signup(userSignUpMapper(singUpRequest)));
            log.info(String.valueOf(jwtResponse));
            return ResponseEntity.ok(jwtResponse);
        }catch (Exception e){
            log.error(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/addVendor")
    public ResponseEntity<JwtResponse> addVendor(@RequestBody SingUpRequest singUpRequest){
        try{

            JwtResponse jwtResponse = returnToken(authenticationService.sigUpVendor(userSignUpMapper(singUpRequest)));
            log.info(String.valueOf(jwtResponse));
            return ResponseEntity.ok(jwtResponse);
        }catch (Exception e){
            log.error(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){
        JwtResponse jwtResponse = returnToken(authenticationService.login(userLoginMapper(loginRequest)));
        log.info(String.valueOf(jwtResponse));
        return ResponseEntity.ok(jwtResponse);
    }
}
