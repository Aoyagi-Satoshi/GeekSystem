package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Long>{

}
