package com.aalmendaris.CarRegistry.rest;

import com.aalmendaris.CarRegistry.controller.CarRegistryController;
import com.aalmendaris.CarRegistry.controller.dtos.carResponse;
import com.aalmendaris.CarRegistry.service.BrandRegistryService;
import com.aalmendaris.CarRegistry.service.CarRegistryService;
import com.aalmendaris.CarRegistry.service.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarRegistryController.class)
public class CarController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRegistryController carRegistryController;

    @MockBean
    private CarRegistryService carRegistryService;

    @MockBean
    private BrandRegistryService brandRegistryService;

    @Test
    @WithMockUser(roles = "CLIENT")
    void tesFoundCar() throws Exception {
        Integer testId=2;
        Car test_Car = new Car(
                2,
                "Ford",
                "fiesta",
                0,
                20000,
                2020,
                "turismo todo incluido",
                "negro",
                "gasolina",
                5
        );

        carResponse testCarResponse = new carResponse(
                test_Car.getId(),
                test_Car.getBrand(),
                test_Car.getModel(),
                test_Car.getMilleage(),
                test_Car.getPrice(),
                test_Car.getYear(),
                test_Car.getDescription(),
                test_Car.getColour(),
                test_Car.getFuelType(),
                test_Car.getNumDoors()
        );

        when(carRegistryService.foundCarService(testId)).thenReturn(test_Car);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/foundCar")
                        .param("id",
                        String.valueOf(testId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(testCarResponse)));
    }

}
