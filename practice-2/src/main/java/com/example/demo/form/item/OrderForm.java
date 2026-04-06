package com.example.demo.form.item;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderForm implements Serializable {

	@NotNull(message = "商品名は必須です")
	private Long itemId;

	@NotNull(message = "発注数は必須です")
	@Min(value = 1, message = "発注数は1以上で入力してください")
	private Integer orderQuantity;

}
