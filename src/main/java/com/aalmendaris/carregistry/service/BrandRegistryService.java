package com.aalmendaris.carregistry.service;

import com.aalmendaris.carregistry.service.model.Brand;
import org.springframework.stereotype.Service;

@Service
public interface BrandRegistryService {
    void addBrandService(Brand brand);
}
