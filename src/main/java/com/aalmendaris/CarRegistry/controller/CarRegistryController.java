package com.aalmendaris.CarRegistry.controller;

import com.aalmendaris.CarRegistry.controller.dtos.brandRequestDTO;
import com.aalmendaris.CarRegistry.controller.dtos.carRequest;
import com.aalmendaris.CarRegistry.controller.dtos.carResponse;
import com.aalmendaris.CarRegistry.service.BrandRegistryService;
import com.aalmendaris.CarRegistry.service.CarRegistryService;
import com.aalmendaris.CarRegistry.service.impl.BrandRegistryServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import static com.aalmendaris.CarRegistry.controller.mappers.BranDtoToBrandMapper.BrandDtoToBrand;
import static com.aalmendaris.CarRegistry.controller.mappers.CarMapperDto.carTocarResponseDto;
import static com.aalmendaris.CarRegistry.controller.mappers.CarMapperDto.dtoToCarMapping;

@RestController
@Slf4j
public class CarRegistryController {
    @Autowired
    private CarRegistryService carRegistryService;
    @Autowired
    private BrandRegistryService brandRegistryService;


    @GetMapping("/allCar")
    public CompletableFuture<?> allCar(){
        return carRegistryService.allCarService().thenApply(ResponseEntity::ok);
    }

    @PostMapping("/addCar")
    public ResponseEntity<?> addCar(@RequestBody carRequest dtoCarRequest) {

        try {
            carRegistryService.addCarService(dtoToCarMapping(dtoCarRequest));
            return ResponseEntity.ok().build();
        }catch (HttpServerErrorException.InternalServerError e){
            log.error(String.valueOf(e));
            return ResponseEntity.internalServerError().build();
        }catch (HttpMessageNotReadableException e){
            log.error("error en los parametros");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/foundCar")
    public ResponseEntity<carResponse> foundCar(@RequestParam Integer id){

            try {
                carResponse car;
                car = carTocarResponseDto(carRegistryService.foundCarService(id));
                return ResponseEntity.ok(car);
            }catch (NoSuchElementException e){
                log.error("No se ha encontrado ningun Vehiculo con el id {}", id);
                return ResponseEntity.notFound().build();
            }catch (Exception e){
                log.error("Ha ocurrido un error en la parte del servidor");
                log.error(String.valueOf(e));
                return ResponseEntity.internalServerError().build();
            }

    }

    @PutMapping("/updateCar")
    public ResponseEntity<carResponse> updateCar(@RequestParam int id, @RequestBody carRequest car){

        try {
            carResponse carResponse = carTocarResponseDto(carRegistryService.updateCarService(id,dtoToCarMapping(car))) ;
            return ResponseEntity.ok(carResponse);
        }catch (NoSuchElementException e){
            log.error("No se ha encontrado ningun Vehiculo para actualizar con el id {}", id);
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            log.error("Ha ocurrido un error en la parte del servidor: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping("/deleteVehiculo")
    public ResponseEntity<?> DeleteCar(@RequestParam int id){
        try {
            return ResponseEntity.ok("Elemento eliminado \n"+carTocarResponseDto(carRegistryService.DeleteCarService(id)));
        }catch (NoSuchElementException e){
            log.error("No se ha encontrado el Vehiculo con el id {} para eliminar", id);
            return ResponseEntity.notFound().build();
        }

    }

    //BRAND

    @PostMapping("/addBrand")
    public ResponseEntity<?> addBrand(@RequestBody brandRequestDTO brandRequest){
        try {
            brandRegistryService.addBrandService(BrandDtoToBrand(brandRequest));
            return ResponseEntity.ok().build();
        }catch (HttpServerErrorException.InternalServerError e){
            log.error(String.valueOf(e));
            return ResponseEntity.internalServerError().build();
        }catch (HttpMessageNotReadableException e){
            log.error("error en los parametros");
            return ResponseEntity.badRequest().build();
        }
    }
}
