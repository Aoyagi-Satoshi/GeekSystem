
package com.example.demo.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.ItemEntity;

public interface ItemService {

	Page<ItemEntity> getAllItem(Pageable pageable);

	Page<ItemEntity> searchItems(
			String itemName,
			Long largeCategoryId,
			Long middleCategoryId,
			Long smallCategoryId,
			Pageable pageable);

	ItemEntity findById(Long id);

}
