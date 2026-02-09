package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StoreEditForm implements Serializable {
	@NotNull
	private Long id;
	@NotBlank
	private String storeName;
	@NotBlank
	private String address;
}
