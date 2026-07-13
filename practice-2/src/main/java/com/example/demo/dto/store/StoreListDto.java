package com.example.demo.dto.store;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class StoreListDto {
	private Long id;
	private String storeName;
	private String address;
}