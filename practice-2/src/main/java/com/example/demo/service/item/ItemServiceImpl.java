package com.example.demo.service.item;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemEntity;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.repository.item.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MessageSource messageSource;

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

	@Override
	public ItemEntity findById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));
	}
}
