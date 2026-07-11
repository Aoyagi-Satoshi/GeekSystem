package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StoreEditForm implements Serializable {

	@NotBlank
	private String storeName;
	@NotBlank
	private String address;
}
