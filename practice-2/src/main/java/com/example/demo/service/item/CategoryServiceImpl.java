package com.example.demo.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Category.LargeCategoryDto;
import com.example.demo.dto.Category.LargeCategoryListDto;
import com.example.demo.dto.Category.MiddleCategoryDto;
import com.example.demo.dto.Category.MiddleCategoryListDto;
import com.example.demo.dto.Category.SmallCategoryDetailDto;
import com.example.demo.dto.Category.SmallCategoryDto;
import com.example.demo.dto.Category.SmallCategoryListDto;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.LargeCategoriesEntity;
import com.example.demo.entity.MiddleCategoriesEntity;
import com.example.demo.entity.SmallCategoriesEntity;
import com.example.demo.repository.item.ItemRepository;
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

	@Autowired
	private ItemRepository itemRepository;

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
		return middleCategoriesRepository.findByLargeCategory_Id(largeCategoryId).stream()
				.map(this::convertToMiddleCategoryDto)
				.toList();
	}

	@Override
	public List<SmallCategoryDto> getSmallCategoriesByMiddleId(Long middleCategoryId) {
		return smallCategoriesRepository.findByMiddleCategory_Id(middleCategoryId).stream()
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

	@Override
	public List<LargeCategoryListDto> getAllLargeList() {
		return largeCategoriesRepository.findAll()
				.stream()
				.map(this::convertToLargeCategoryListDto)
				.toList();
	}

	private LargeCategoryListDto convertToLargeCategoryListDto(
			LargeCategoriesEntity entity) {

		LargeCategoryListDto dto = new LargeCategoryListDto();

		dto.setId(entity.getId());
		dto.setLargeName(entity.getLargeName());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	@Override
	public List<MiddleCategoryListDto> getMiddleListByLargeId(Long largeCategoryId) {
		return middleCategoriesRepository.findByLargeCategory_Id(largeCategoryId)
				.stream()
				.map(this::convertToMiddleCategoryListDto)
				.toList();
	}

	private MiddleCategoryListDto convertToMiddleCategoryListDto(
			MiddleCategoriesEntity entity) {

		MiddleCategoryListDto dto = new MiddleCategoryListDto();

		dto.setId(entity.getId());
		dto.setMiddleName(entity.getMiddleName());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	@Override
	public List<SmallCategoryListDto> getSmallListByMiddleId(Long middleCategoryId) {
		return smallCategoriesRepository.findByMiddleCategory_Id(middleCategoryId)
				.stream()
				.map(this::convertToSmallCategoryListDto)
				.toList();
	}

	private SmallCategoryListDto convertToSmallCategoryListDto(
			SmallCategoriesEntity entity) {

		SmallCategoryListDto dto = new SmallCategoryListDto();

		dto.setId(entity.getId());
		dto.setSmallName(entity.getSmallName());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}

	@Override
	public List<SmallCategoryDetailDto> getSmallCategoryDetail(Long smallCategoryId) {
		return itemRepository.findBySmallCategory_Id(smallCategoryId).stream()
				.map(this::convertToSmallCategoryDetailDto)
				.toList();
	}

	private SmallCategoryDetailDto convertToSmallCategoryDetailDto(ItemEntity item) {
		SmallCategoryDetailDto dto = new SmallCategoryDetailDto();
		dto.setId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setUpdatedAt(item.getUpdatedAt());
		return dto;
	}

	@Override
	public Long getLargeCategoryIdByMiddleCategoryId(Long middleCategoryId) {
		MiddleCategoriesEntity middle = middleCategoriesRepository.findById(middleCategoryId)
				.orElseThrow();

		return middle.getLargeCategory().getId();
	}

	@Override
	public Long getMiddleCategoryIdBySmallCategoryId(Long smallCategoryId) {
		SmallCategoriesEntity small = smallCategoriesRepository.findById(smallCategoryId)
				.orElseThrow();

		return small.getMiddleCategory().getId();
	}
}
