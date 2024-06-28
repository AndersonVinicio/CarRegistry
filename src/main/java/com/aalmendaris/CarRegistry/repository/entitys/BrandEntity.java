package com.aalmendaris.CarRegistry.repository.entitys;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBrand")
    private Integer idBrand;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "warranty", nullable = false)
    private int warranty;

    @Column(name = "country", nullable = false)
    private  String country;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CarEntity> vehiculos;
}
