package com.aalmendaris.carregistry.service.mappers;

import com.aalmendaris.carregistry.repository.entitys.BrandEntity;
import com.aalmendaris.carregistry.service.model.Brand;

public class BrandToBrandEntityMapper {
    public static Brand BrandEntityToBrand(BrandEntity brandEntity){
        return new Brand(
                brandEntity.getIdBrand(),
                brandEntity.getName(),
                brandEntity.getWarranty(),
                brandEntity.getCountry()
        );
    }

    public static BrandEntity BrandToBrandEntity(Brand brand){
        return BrandEntity.builder()
                .idBrand(brand.getIdBrand())
                .name(brand.getName())
                .warranty(brand.getWarranty())
                .country(brand.getCountry())
                .build();
    }


}
