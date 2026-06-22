package com.example.demo.service.item;

import java.util.List;

import com.example.demo.dto.Category.LargeCategoryDto;
import com.example.demo.dto.Category.LargeCategoryListDto;
import com.example.demo.dto.Category.MiddleCategoryDto;
import com.example.demo.dto.Category.MiddleCategoryListDto;
import com.example.demo.dto.Category.SmallCategoryDetailDto;
import com.example.demo.dto.Category.SmallCategoryDto;
import com.example.demo.dto.Category.SmallCategoryListDto;

public interface CategoryService {
	List<LargeCategoryDto> getAllLargeCategories();

	List<MiddleCategoryDto> getAllMiddleCategories();

	List<SmallCategoryDto> getAllSmallCategories();

	List<MiddleCategoryDto> getMiddleCategoriesByLargeId(Long largeCategoryId);

	List<SmallCategoryDto> getSmallCategoriesByMiddleId(Long middleCategoryId);

	List<LargeCategoryListDto> getAllLargeList();

	List<MiddleCategoryListDto> getAllMiddleList();

	List<SmallCategoryListDto> getAllSmallList();

	List<SmallCategoryDetailDto> getSmallCategoryDetail(Long smallCategoryId);
}