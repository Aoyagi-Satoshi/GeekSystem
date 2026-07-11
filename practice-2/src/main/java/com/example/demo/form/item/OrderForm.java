package com.example.demo.form.item;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderForm implements Serializable {

	@NotNull(message = "{order.itemId.required}")
	private Long itemId;

	@NotNull(message = "{order.quantity.required}")
	@Min(value = 1, message = "{order.quantity.min}")
	private Integer orderQuantity;

}
