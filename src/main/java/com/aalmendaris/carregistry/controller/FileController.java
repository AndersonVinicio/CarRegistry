package com.aalmendaris.carregistry.controller;

import com.aalmendaris.carregistry.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;


    @PostMapping(value = "/UploadImgUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
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

    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    @GetMapping(value = "/downloaderImgUser/{id}")
    public ResponseEntity<byte[]> downLoderImg(@PathVariable int id){
        try{
            byte[]imgUser = fileService.downloadImgService(id);
            if(imgUser!=null && imgUser.length>0){
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .contentLength(imgUser.length)
                        .body(imgUser);
            }
            return ResponseEntity.ok("el usuario no tien imagen".getBytes());

        }catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body("El usuario no existe".getBytes());
        }catch (Exception e){
            log.error("erro {}",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage().getBytes());
        }
    }

    @GetMapping("/dataCars")
    @PreAuthorize("hasAnyRole('CLIENT','VENDOR')")
    public ResponseEntity<byte[]>downloadCarsCsv(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "cars.csv");
        byte[] csvByte = fileService.carsCsv().getBytes();
        return new ResponseEntity<>(csvByte,headers, HttpStatus.OK);
    }


    @PostMapping(value = "/addCarsCsv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String>addCarCsv(@RequestPart(value = "file") MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
           return ResponseEntity.badRequest().body("EL ARCHIVO ESTA VACIO ");
        }
        try{
            String filname = multipartFile.getOriginalFilename();
            if(filname !=null && filname.contains(".csv")){
                return ResponseEntity.ok(fileService.addCarsCsv(multipartFile));
            }
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.badRequest().body("EL ARCHIVO NO ES UN CSV");

    }
}
