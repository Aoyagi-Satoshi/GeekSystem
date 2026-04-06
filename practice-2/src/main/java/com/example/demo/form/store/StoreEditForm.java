package com.example.demo.form.store;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StoreEditForm implements Serializable {

	@NotNull
	private Long id;

	@NotBlank(message = "店舗名は必須です")
	private String storeName;

	@NotBlank(message = "住所は必須です")
	private String address;
}
