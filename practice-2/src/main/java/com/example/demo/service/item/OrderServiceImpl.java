package com.example.demo.service.item;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.item.OrderItemDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.OrderHistoryEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.entity.StoreItemEntity;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.StoreItemNotFoundException;
import com.example.demo.exception.StoreNotFoundException;
import com.example.demo.repository.item.ItemRepository;
import com.example.demo.repository.item.OrderHistoryRepository;
import com.example.demo.repository.store.StoreItemRepository;
import com.example.demo.repository.store.StoreRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private StoreItemRepository storeItemRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Override
	public ItemEntity findById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));
	}

	@Override
	public StoreItemEntity findByStoreAndItem(Long storeId, Long itemId) {
		StoreEntity store = storeRepository.findById(storeId)
				.orElseThrow(() -> new StoreNotFoundException(
						messageSource.getMessage("store.notfound", null, Locale.getDefault())));
		ItemEntity item = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));
		return storeItemRepository.findByStoreAndItem(store, item)
				.orElseThrow(() -> new StoreItemNotFoundException(
						messageSource.getMessage("storeitem.notfound", null, Locale.getDefault())));
	}

	@Override
	@Transactional
	public void orderItem(Long storeId, Long itemId, Integer orderQuantity, AdminEntity admin) {
		StoreEntity store = storeRepository.findById(storeId)
				.orElseThrow(() -> new StoreNotFoundException(
						messageSource.getMessage("store.notfound", null, Locale.getDefault())));

		ItemEntity item = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));

		StoreItemEntity storeItem = storeItemRepository.findByStoreAndItem(store, item)
				.orElseThrow(() -> new StoreItemNotFoundException(
						messageSource.getMessage("storeitem.notfound", null, Locale.getDefault())));

		storeItem.setStock(storeItem.getStock() + orderQuantity);
		storeItemRepository.save(storeItem);

		OrderHistoryEntity history = new OrderHistoryEntity();
		history.setAdmin(admin);
		history.setStoreItem(storeItem);
		history.setOrderCount(orderQuantity);
		orderHistoryRepository.save(history);
	}

	@Override
	public OrderItemDto getOrderItem(Long storeId, Long itemId) {
		StoreEntity store = storeRepository.findById(storeId)
				.orElseThrow(() -> new StoreNotFoundException(
						messageSource.getMessage("store.notfound", null, Locale.getDefault())));

		ItemEntity item = itemRepository.findById(itemId)
				.orElseThrow(() -> new ItemNotFoundException(
						messageSource.getMessage("item.notfound", null, Locale.getDefault())));

		StoreItemEntity storeItem = storeItemRepository.findByStoreAndItem(store, item)
				.orElseThrow(() -> new StoreItemNotFoundException(
						messageSource.getMessage("storeitem.notfound", null, Locale.getDefault())));

		return convertToOrderItemDto(item, storeItem);
	}

	private OrderItemDto convertToOrderItemDto(ItemEntity item, StoreItemEntity storeItem) {
		OrderItemDto dto = new OrderItemDto();
		dto.setId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setStock(storeItem.getStock());
		return dto;
	}

}