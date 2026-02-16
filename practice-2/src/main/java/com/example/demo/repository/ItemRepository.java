package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
