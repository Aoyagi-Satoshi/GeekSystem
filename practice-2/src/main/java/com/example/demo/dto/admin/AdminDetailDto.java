package com.example.demo.dto.admin;

import lombok.Data;

@Data
public class AdminDetailDto {
	private Long id;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String storeName;
	private String roleName;
	private String permissionName;
}
