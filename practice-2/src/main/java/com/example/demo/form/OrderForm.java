package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderForm implements Serializable {

	@NotNull
	private Long itemId;
	@NotNull
	@Min(1)
	private Integer orderQuantity;

}
