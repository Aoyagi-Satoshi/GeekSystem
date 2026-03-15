package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.LargeCategoriesEntity;
import com.example.demo.entity.MiddleCategoriesEntity;
import com.example.demo.entity.SmallCategoriesEntity;

public interface CategoryService {
	List<LargeCategoriesEntity> getAllLargeCategories();

	List<MiddleCategoriesEntity> getAllMiddleCategories();

	List<SmallCategoriesEntity> getAllSmallCategories();

	List<MiddleCategoriesEntity> getMiddleCategoriesByLargeId(Long largeCategoryId);

	List<SmallCategoriesEntity> getSmallCategoriesByMiddleId(Long middleCategoryId);

}
