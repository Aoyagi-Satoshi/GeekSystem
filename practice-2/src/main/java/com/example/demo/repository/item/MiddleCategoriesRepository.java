package com.example.demo.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MiddleCategoriesEntity;

public interface MiddleCategoriesRepository extends JpaRepository<MiddleCategoriesEntity, Long> {
	List<MiddleCategoriesEntity> findByLargeCategory_Id(Long largeCategoryId);
}
