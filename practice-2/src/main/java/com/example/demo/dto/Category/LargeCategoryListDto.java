package com.example.demo.dto.Category;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class LargeCategoryListDto {
	private Long id;
	private String largeName;
	private Timestamp updatedAt;
}
