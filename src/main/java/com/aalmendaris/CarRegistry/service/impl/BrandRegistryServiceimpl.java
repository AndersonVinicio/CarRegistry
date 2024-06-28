package com.aalmendaris.CarRegistry.service.impl;

import com.aalmendaris.CarRegistry.repository.BrandRepository;
import com.aalmendaris.CarRegistry.service.BrandRegistryService;
import com.aalmendaris.CarRegistry.service.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aalmendaris.CarRegistry.service.mappers.BrandToBrandEntityMapper.BrandToBrandEntity;

@Service
public class BrandRegistryServiceimpl implements BrandRegistryService {
    @Autowired
    BrandRepository brandRepository;


    @Override
    public void addBrandService(Brand brand) {
        brandRepository.save(BrandToBrandEntity(brand));
    }
}
