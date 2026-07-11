package com.example.demo.dto.item;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ItemListDto {
	private Long id;
	private String itemName;
	private String makerName;
	private String largeCategoryName;
	private String middleCategoryName;
	private String smallCategoryName;
	private BigDecimal costPrice;
	private Timestamp createdAt;
}
