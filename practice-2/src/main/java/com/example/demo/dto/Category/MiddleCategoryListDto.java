package com.example.demo.dto.Category;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MiddleCategoryListDto {
	private Long id;
	private String MiddleName;
	private Timestamp updatedAt;
}
