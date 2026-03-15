package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemEntity;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Page<ItemEntity> getAllItem(Pageable pageable) {
		return itemRepository.findAll(pageable);
	}

	@Override
	public Page<ItemEntity> searchItems(
			String itemName,
			Long largeCategoryId,
			Long middleCategoryId,
			Long smallCategoryId,
			Pageable pageable) {
		String keyword;
		if (itemName != null) {
			keyword = itemName.trim();
		} else {
			keyword = "";
		}

		return itemRepository.searchItems(
				keyword,
				largeCategoryId,
				middleCategoryId,
				smallCategoryId,
				pageable);
	}

}
