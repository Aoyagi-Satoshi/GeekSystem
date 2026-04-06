
package com.example.demo.service.item;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.StoreItemEntity;

public interface OrderService {
	ItemEntity findById(Long id);

	public void orderItem(Long storeId, Long itemId, Integer orderQuantity);

	public StoreItemEntity findByStoreAndItem(Long storeId, Long itemId);
}
