package com.example.demo.service.item;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.item.OrderHistoryDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.OrderHistoryEntity;
import com.example.demo.entity.StoreItemEntity;
import com.example.demo.repository.item.OrderHistoryRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Override
	public List<OrderHistoryDto> getOrderHistories() {
		return orderHistoryRepository.findAll().stream()
				.map(this::convertToOrderHistoryDto)
				.toList();
	}

	private OrderHistoryDto convertToOrderHistoryDto(OrderHistoryEntity history) {
		StoreItemEntity storeItem = history.getStoreItem();
		ItemEntity item = storeItem.getItem();
		AdminEntity admin = history.getAdmin();

		OrderHistoryDto dto = new OrderHistoryDto();
		dto.setItemId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setOrderCount(history.getOrderCount());
		dto.setAdminName(admin.getLastName() + admin.getFirstName());
		dto.setTotalPrice(item.getCostPrice().multiply(BigDecimal.valueOf(history.getOrderCount())));
		dto.setUpdatedAt(history.getUpdatedAt());
		return dto;
	}
}
