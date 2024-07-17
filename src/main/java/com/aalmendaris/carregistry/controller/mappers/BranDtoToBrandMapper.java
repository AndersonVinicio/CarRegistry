package com.aalmendaris.carregistry.controller.mappers;

import com.aalmendaris.carregistry.controller.dtos.brandRequestDTO;
import com.aalmendaris.carregistry.controller.dtos.brandResponseDTO;
import com.aalmendaris.carregistry.service.model.Brand;

public class BranDtoToBrandMapper {
    public static Brand BrandDtoToBrand(brandRequestDTO brandDTO){
        return Brand.builder()
                .name(brandDTO.getName())
                .warranty(brandDTO.getWarranty())
                .country(brandDTO.getCountry())
                .build();
    }

    public static brandResponseDTO BrandToBrandDTO ( Brand brand){
        return new brandResponseDTO(
                brand.getIdBrand(),
                brand.getName(),
                brand.getWarranty(),
                brand.getCountry()
        );
    }
}
