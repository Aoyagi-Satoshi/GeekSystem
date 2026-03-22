package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.entity.StoreItemEntity;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StoreItemRepository;
import com.example.demo.repository.StoreRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private StoreItemRepository storeItemRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public ItemEntity findById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("商品が見つかりません"));
	}

	@Override
	public StoreItemEntity findByStoreAndItem(Long storeId, Long itemId) {
		StoreEntity store = storeRepository.findById(storeId)
				.orElseThrow(() -> new RuntimeException("店舗が見つかりません"));

		ItemEntity item = itemRepository.findById(itemId)
				.orElseThrow(() -> new RuntimeException("商品が見つかりません"));

		return storeItemRepository.findByStoreAndItem(store, item)
				.orElseThrow(() -> new RuntimeException("店舗別商品が見つかりません"));
	}

	@Override
	@Transactional
	public void orderItem(Long storeId, Long itemId, Integer orderQuantity) {

		StoreEntity store = storeRepository.findById(storeId)
				.orElseThrow(() -> new RuntimeException("店舗が見つかりません"));

		ItemEntity item = itemRepository.findById(itemId)
				.orElseThrow(() -> new RuntimeException("商品が見つかりません"));

		StoreItemEntity storeItem = storeItemRepository.findByStoreAndItem(store, item)
				.orElseThrow(() -> new RuntimeException("店舗別商品が見つかりません"));

		storeItem.setStock(storeItem.getStock() + orderQuantity);

		storeItemRepository.save(storeItem);

	}
}
