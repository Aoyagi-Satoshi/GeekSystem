package com.example.demo.dto.Category;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class SmallCategoryListDto {
	private Long id;
	private String smallName;
	private Timestamp updatedAt;
}
