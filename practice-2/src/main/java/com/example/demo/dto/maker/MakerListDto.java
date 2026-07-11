package com.example.demo.dto.maker;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MakerListDto {
	private Long id;
	private String makerName;
	private Timestamp createdAt;
}