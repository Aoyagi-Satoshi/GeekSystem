package com.example.demo.service.item;

import java.util.List;

import com.example.demo.dto.Category.LargeCategoryDto;
import com.example.demo.dto.Category.MiddleCategoryDto;
import com.example.demo.dto.Category.SmallCategoryDto;

public interface CategoryService {
	List<LargeCategoryDto> getAllLargeCategories();

	List<MiddleCategoryDto> getAllMiddleCategories();

	List<SmallCategoryDto> getAllSmallCategories();

	List<MiddleCategoryDto> getMiddleCategoriesByLargeId(Long largeCategoryId);

	List<SmallCategoryDto> getSmallCategoriesByMiddleId(Long middleCategoryId);

}
