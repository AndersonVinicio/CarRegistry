package com.aalmendaris.carregistry.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/Auth")
public class FileController {

    @PostMapping(value = "/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile){
        try {
            if(multipartFile.isEmpty()){
                log.error("the file it`s empty");
                return ResponseEntity.badRequest().body("el archivo esta vacio");
            }

            log.info("El archivo con el nombre {}",multipartFile.getOriginalFilename());
            return ResponseEntity.ok("se ha subido el archivo correctamente");

        }catch (Exception e){
            log.error("este es el error del try cath: {}",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
