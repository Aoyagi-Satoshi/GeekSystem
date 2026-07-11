package com.example.demo.dto.Category;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SmallCategoryDetailDto {
	private Long id;
	private String itemName;
	private Timestamp updatedAt;
}