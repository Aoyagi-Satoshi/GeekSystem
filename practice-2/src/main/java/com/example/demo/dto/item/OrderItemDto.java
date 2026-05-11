package com.example.demo.dto.item;

import lombok.Data;

@Data
public class OrderItemDto {
	private Long id;
	private String itemName;
	private Integer stock;
}