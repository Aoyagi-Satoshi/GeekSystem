package com.example.demo.dto.item;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemDetailDto {
	private Long id;
	private String itemName;
	private String largeCategoryName;
	private BigDecimal costPrice;
	private BigDecimal makerPrice;
	private String makerName;
	private String itemInfo;
}
