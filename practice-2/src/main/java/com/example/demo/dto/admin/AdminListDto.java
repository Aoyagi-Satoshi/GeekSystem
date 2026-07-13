package com.example.demo.dto.admin;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class AdminListDto {
	private Long id;
	private String lastName;
	private String firstName;
	private String storeName;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
