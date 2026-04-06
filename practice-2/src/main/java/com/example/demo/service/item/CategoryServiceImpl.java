package com.example.demo.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<LargeCategoriesEntity> getAllLargeCategories() {
		return largeCategoriesRepository.findAll();
	}

	@Override
	public List<MiddleCategoriesEntity> getAllMiddleCategories() {
		return middleCategoriesRepository.findAll();
	}

	@Override
	public List<SmallCategoriesEntity> getAllSmallCategories() {
		return smallCategoriesRepository.findAll();
	}

	@Override
	public List<MiddleCategoriesEntity> getMiddleCategoriesByLargeId(Long largeCategoryId) {

		return middleCategoriesRepository.findByLargeCategoryId(largeCategoryId);
	}

	@Override
	public List<SmallCategoriesEntity> getSmallCategoriesByMiddleId(Long middleCategoryId) {

		return smallCategoriesRepository.findByMiddleCategoryId(middleCategoryId);
	}
}
