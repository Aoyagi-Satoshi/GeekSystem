package com.example.demo.form.store;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StoreEditForm implements Serializable {

	@NotNull
	private Long id;

	@NotBlank(message = "{store.name.required}")
	private String storeName;

	@NotBlank(message = "{store.address.required}")
	private String address;
}
