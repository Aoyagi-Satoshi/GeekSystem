package com.example.demo.dto.Category;

import lombok.Data;

@Data
public class SmallCategoryDto {
	private Long id;
	private String smallName;
	private Long middleCategoryId;
	private Long largeCategoryId;
}