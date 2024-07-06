package com.aalmendaris.CarRegistry.repository.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int idUser;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
