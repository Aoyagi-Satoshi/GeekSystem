package com.example.demo.service.item;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.item.ItemDetailDto;
import com.example.demo.dto.item.ItemListDto;
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
	public Page<ItemListDto> getAllItem(Pageable pageable) {
		return itemRepository.findAll(pageable)
				.map(this::convertToItemListDto);
	}

	@Override
	public Page<ItemListDto> searchItems(
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
				pageable)
				.map(this::convertToItemListDto);
	}

	private ItemListDto convertToItemListDto(ItemEntity item) {
		ItemListDto dto = new ItemListDto();
		dto.setId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setCostPrice(item.getCostPrice());
		dto.setCreatedAt(item.getCreatedAt());
		if (item.getMaker() != null) {
			dto.setMakerName(item.getMaker().getMakerName());
		}
		if (item.getSmallCategory() != null) {
			dto.setSmallCategoryName(item.getSmallCategory().getSmallName());
		}
		return dto;
	}

	@Override
	public ItemEntity findById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));
	}

	@Override
	public ItemDetailDto getDetailItem(Long id) {
		ItemEntity item = itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));

		ItemDetailDto dto = new ItemDetailDto();

		dto.setId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setCostPrice(item.getCostPrice());
		dto.setMakerPrice(item.getMakerPrice());
		dto.setItemInfo(item.getItemInfo());

		if (item.getMaker() != null) {
			dto.setMakerName(item.getMaker().getMakerName());
		}

		if (item.getSmallCategory() != null
				&& item.getSmallCategory().getMiddleCategory() != null
				&& item.getSmallCategory().getMiddleCategory().getLargeCategory() != null) {

			dto.setLargeCategoryName(
					item.getSmallCategory()
							.getMiddleCategory()
							.getLargeCategory()
							.getLargeName());
		}

		return dto;
	}
}
