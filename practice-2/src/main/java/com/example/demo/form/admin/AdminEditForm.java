package com.example.demo.form.admin;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminEditForm implements Serializable {

	@NotNull
	private Long id;

	@NotNull(message = "店舗名は必須です")
	private Long storeId;

	@NotBlank(message = "姓は必須です")
	private String lastName;

	@NotBlank(message = "名は必須です")
	private String firstName;

	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;

	@NotNull(message = "役職は必須です")
	private Long roleId;

	@NotNull(message = "権限は必須です")
	private Long permissionId;

	@NotBlank(message = "電話番号は必須です")
	@Size(min = 10, max = 11, message = "電話番号は10桁または11桁で入力してください")
	private String phone;
}
