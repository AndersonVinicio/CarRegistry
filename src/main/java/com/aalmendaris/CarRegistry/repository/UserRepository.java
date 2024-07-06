package com.aalmendaris.CarRegistry.repository;

import com.aalmendaris.CarRegistry.repository.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
