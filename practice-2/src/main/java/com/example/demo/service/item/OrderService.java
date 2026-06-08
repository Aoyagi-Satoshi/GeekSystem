
package com.example.demo.service.item;

import com.example.demo.dto.item.OrderItemDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.StoreItemEntity;

public interface OrderService {
	ItemEntity findById(Long id);

	void orderItem(Long storeId, Long itemId, Integer orderQuantity, AdminEntity admin);

	public StoreItemEntity findByStoreAndItem(Long storeId, Long itemId);

	OrderItemDto getOrderItem(Long storeId, Long itemId);
}
