package com.aalmendaris.carregistry.service.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    private int idBrand;
    @NonNull
    private String name;
    private int warranty;
    @NonNull
    private String country;
}
