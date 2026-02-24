package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SmallCategoriesEntity;

public interface SmallCategoriesRepository extends JpaRepository<SmallCategoriesEntity, Long> {

}
