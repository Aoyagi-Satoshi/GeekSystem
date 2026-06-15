package com.example.demo.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Category.LargeCategoryDto;
import com.example.demo.dto.Category.MiddleCategoryDto;
import com.example.demo.dto.Category.SmallCategoryDto;
import com.example.demo.entity.LargeCategoriesEntity;
import com.example.demo.entity.MiddleCategoriesEntity;
import com.example.demo.entity.SmallCategoriesEntity;
import com.example.demo.repository.item.LargeCategoriesRepository;
import com.example.demo.repository.item.MiddleCategoriesRepository;
import com.example.demo.repository.item.SmallCategoriesRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private LargeCategoriesRepository largeCategoriesRepository;

	@Autowired
	private MiddleCategoriesRepository middleCategoriesRepository;

	@Autowired
	private SmallCategoriesRepository smallCategoriesRepository;

	@Override
	public List<LargeCategoryDto> getAllLargeCategories() {
		return largeCategoriesRepository.findAll().stream()
				.map(this::convertToLargeCategoryDto)
				.toList();
	}

	@Override
	public List<MiddleCategoryDto> getAllMiddleCategories() {
		return middleCategoriesRepository.findAll().stream()
				.map(this::convertToMiddleCategoryDto)
				.toList();
	}

	@Override
	public List<SmallCategoryDto> getAllSmallCategories() {
		return smallCategoriesRepository.findAll().stream()
				.map(this::convertToSmallCategoryDto)
				.toList();
	}

	@Override
	public List<MiddleCategoryDto> getMiddleCategoriesByLargeId(Long largeCategoryId) {
		return middleCategoriesRepository.findByLargeCategoryId(largeCategoryId).stream()
				.map(this::convertToMiddleCategoryDto)
				.toList();
	}

	@Override
	public List<SmallCategoryDto> getSmallCategoriesByMiddleId(Long middleCategoryId) {
		return smallCategoriesRepository.findByMiddleCategoryId(middleCategoryId).stream()
				.map(this::convertToSmallCategoryDto)
				.toList();
	}

	private LargeCategoryDto convertToLargeCategoryDto(LargeCategoriesEntity entity) {
		LargeCategoryDto dto = new LargeCategoryDto();
		dto.setId(entity.getId());
		dto.setLargeName(entity.getLargeName());
		return dto;
	}

	private MiddleCategoryDto convertToMiddleCategoryDto(MiddleCategoriesEntity entity) {
		MiddleCategoryDto dto = new MiddleCategoryDto();
		dto.setId(entity.getId());
		dto.setMiddleName(entity.getMiddleName());
		dto.setLargeCategoryId(entity.getLargeCategory().getId());
		return dto;
	}

	private SmallCategoryDto convertToSmallCategoryDto(SmallCategoriesEntity entity) {
		SmallCategoryDto dto = new SmallCategoryDto();
		dto.setId(entity.getId());
		dto.setSmallName(entity.getSmallName());
		dto.setMiddleCategoryId(entity.getMiddleCategory().getId());
		dto.setLargeCategoryId(entity.getMiddleCategory().getLargeCategory().getId());
		return dto;
	}

}
