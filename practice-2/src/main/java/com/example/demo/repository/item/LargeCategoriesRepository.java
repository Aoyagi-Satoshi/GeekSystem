package com.example.demo.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LargeCategoriesEntity;

public interface LargeCategoriesRepository extends JpaRepository<LargeCategoriesEntity, Long> {
}
