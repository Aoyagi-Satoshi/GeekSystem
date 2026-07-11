package com.example.demo.dto.maker;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MakerItemDto {
	private Long id;
	private String itemName;
	private String smallCategoryName;
	private Timestamp createdAt;
}
