package com.aalmendaris.carregistry.service.impl;

import com.aalmendaris.carregistry.repository.BrandRepository;
import com.aalmendaris.carregistry.repository.CarRegistryRepository;
import com.aalmendaris.carregistry.repository.UserRepository;
import com.aalmendaris.carregistry.repository.entitys.CarEntity;
import com.aalmendaris.carregistry.repository.entitys.UserEntity;
import com.aalmendaris.carregistry.service.FileService;
import com.aalmendaris.carregistry.service.mappers.CarMapperEntity;
import com.aalmendaris.carregistry.service.mappers.UserToUserEntityMapper;
import com.aalmendaris.carregistry.service.model.Car;
import com.aalmendaris.carregistry.service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final UserRepository userRepository;
    private final CarRegistryRepository carRegistryRepository;
    private final BrandRepository brandRepository;
    
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

    @Override
    public byte[] downloadImgService(int idUser) {
        Optional<UserEntity> userOptional = Optional.ofNullable(userRepository.findById(idUser)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado")));
        User user = UserToUserEntityMapper.UserEntityToUser(userOptional.get());
        byte[] imgUser = user.getImg();
        if (imgUser != null && imgUser.length > 0){
            return imgUser;
        }
        return new byte[0];
    }

    @Override
    public String carsCsv() {
        String[] HEADER = {"BRAND",
                "MODEL",
                "MILLEAGE",
                "PRICE",
                "YEAR",
                "DESCRIPTION",
                "COLOUR",
                "FUELTYPE",
                "NUMDOORS"};
        List<CarEntity> carEntityList = carRegistryRepository.findAll();
        StringBuilder csvContent = new StringBuilder();
        if (!carEntityList.isEmpty()){
            List<Car> carsList = CarMapperEntity.carEntityToCarList(carEntityList);
            csvContent.append(String.join(",",HEADER)).append("\n");
            for (Car car: carsList){
                csvContent.append(car.getBrand())
                        .append(",")
                        .append(car.getModel())
                        .append(",")
                        .append(car.getMilleage())
                        .append(",")
                        .append(car.getPrice())
                        .append(",")
                        .append(car.getYear())
                        .append(",")
                        .append(car.getDescription())
                        .append(",")
                        .append(car.getColour())
                        .append(",")
                        .append(car.getFuelType())
                        .append(",")
                        .append(car.getNumDoors())
                        .append("\n");
            }
        }
        return csvContent.toString();
    }

    @Override
    public String addCarsCsv(MultipartFile multipartFile) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(),"UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.builder().setTrim(true).setIgnoreHeaderCase(true).setHeader().setSkipHeaderRecord(true).build())){

            List<Car> newCarsList = new ArrayList<>();
            Iterable<CSVRecord>carsList=csvParser.getRecords();

            for (CSVRecord car : carsList){
                Car newCar = Car.builder()
                        .brand(car.get("BRAND"))
                        .model(car.get("MODEL"))
                        .Milleage( Integer.parseInt(car.get("MILLEAGE")))
                        .Price( Double.parseDouble(car.get("PRICE")))
                        .Year( Integer.parseInt(car.get("YEAR")))
                        .Description(car.get("DESCRIPTION"))
                        .Colour(car.get("COLOUR"))
                        .FuelType(car.get("FUELTYPE"))
                        .NumDoors( Integer.parseInt(car.get("NUMDOORS")))
                        .build();
                newCarsList.add(newCar);
            }
            List<CarEntity> newCarsEntityList = new ArrayList<>();
            for (Car car : newCarsList){
                newCarsEntityList.add(
                        CarMapperEntity.carToCarEntity(
                                car, brandRepository.findByName(car.getBrand())));
            }

            carRegistryRepository.saveAll(newCarsEntityList);

            return "SE HAN AGREGADO CORRECTAMENTE LOS COCHES DEL FICHERO CSV";

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
