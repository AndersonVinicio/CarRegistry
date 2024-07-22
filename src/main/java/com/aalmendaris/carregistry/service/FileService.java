package com.aalmendaris.carregistry.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String addImgUser(MultipartFile multipartFile, Integer id) throws IOException;
    byte[] downloadImgService(int idUser);
    String carsCsv();
    String addCarsCsv(MultipartFile multipartFile);
}
