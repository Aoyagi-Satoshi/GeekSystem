
package com.example.demo.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.item.ItemListDto;
import com.example.demo.entity.ItemEntity;

public interface ItemService {

	Page<ItemListDto> getAllItem(Pageable pageable);

	Page<ItemListDto> searchItems(
			String itemName,
			Long largeCategoryId,
			Long middleCategoryId,
			Long smallCategoryId,
			Pageable pageable);

	ItemEntity findById(Long id);

}
