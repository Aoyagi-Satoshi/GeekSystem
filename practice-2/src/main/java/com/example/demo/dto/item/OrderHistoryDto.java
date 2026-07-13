package com.example.demo.dto.item;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderHistoryDto {
	private Long itemId;
	private String itemName;
	private int orderCount;
	private String adminName;
	private BigDecimal totalPrice;
	private Timestamp updatedAt;
}
