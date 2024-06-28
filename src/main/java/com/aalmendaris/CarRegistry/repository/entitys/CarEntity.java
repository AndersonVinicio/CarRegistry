package com.aalmendaris.CarRegistry.repository.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    /*
     *@GeneratedValue
     * INDICA QUE LA BD SE ENCARGA DE GENERAR EL ID
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCar")
    private Integer id;

    @ManyToOne
    @JoinColumn(name= "brand_id", nullable = false)
    private BrandEntity brand;

    @Column(name = "model",nullable = false)
    private String model;

    @Column(name = "milleage",nullable = false)
    private int milleage;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "year",nullable = false)
    private int year;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "colour",nullable = false)
    private String colour;

    @Column(name = "fuelType",nullable = false)
    private String fuelType;

    @Column(name = "numDoors",nullable = false)
    private int numDoors;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updated_at;
}
