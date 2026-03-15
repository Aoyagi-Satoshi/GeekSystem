package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SmallCategoriesEntity;

public interface SmallCategoriesRepository extends JpaRepository<SmallCategoriesEntity, Long> {
	List<SmallCategoriesEntity> findByMiddleCategoryId(Long middleCategoryId);
}
