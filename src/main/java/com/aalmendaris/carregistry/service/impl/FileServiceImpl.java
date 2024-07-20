package com.aalmendaris.carregistry.service.impl;

import com.aalmendaris.carregistry.repository.UserRepository;
import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.FileService;
import com.aalmendaris.carregistry.service.mappers.UserToUserEntityMapper;
import com.aalmendaris.carregistry.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final UserRepository userRepository;

    @Override
    public String addImgUser(MultipartFile multipartFile, Integer id){
        Optional<UserEntity> foundUser = userRepository.findById(id);

        if (foundUser.isPresent()){
            User updateUser = UserToUserEntityMapper.UserEntityToUser(foundUser.get());

            try {
                updateUser.setImg(multipartFile.getBytes());
                userRepository.save(UserToUserEntityMapper.UserToUserEntity(updateUser));
                return "Se ha guradado la imagen en la BD";
            } catch (IOException e) {
                return "No se ha podido agregar a BD "+ "por el error al convertir el archivo en bytes" ;
            }
        }
        return "No se ha guardado en la BD";
    }
}
