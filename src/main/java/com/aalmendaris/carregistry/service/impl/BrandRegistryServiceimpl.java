package com.aalmendaris.carregistry.service.impl;

import com.aalmendaris.carregistry.repository.BrandRepository;
import com.aalmendaris.carregistry.service.BrandRegistryService;
import com.aalmendaris.carregistry.service.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aalmendaris.carregistry.service.mappers.BrandToBrandEntityMapper.BrandToBrandEntity;

@Service
public class BrandRegistryServiceimpl implements BrandRegistryService {
    @Autowired
    BrandRepository brandRepository;


    @Override
    public void addBrandService(Brand brand) {
        brandRepository.save(BrandToBrandEntity(brand));
    }
}
