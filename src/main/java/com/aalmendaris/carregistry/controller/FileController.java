package com.aalmendaris.carregistry.controller;

import com.aalmendaris.carregistry.service.FileService;
import com.aalmendaris.carregistry.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/UploadImgUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile, @RequestParam int idUser){
        try {
            if(multipartFile.isEmpty()){
                log.error("the file it`s empty");
                return ResponseEntity.badRequest().body("el archivo esta vacio");
            }

            log.info("El archivo con el nombre {}",multipartFile.getOriginalFilename());
            return ResponseEntity.ok(fileService.addImgUser(multipartFile,idUser));

        }catch (Exception e){
            log.error("este es el error del try cath: {}",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
