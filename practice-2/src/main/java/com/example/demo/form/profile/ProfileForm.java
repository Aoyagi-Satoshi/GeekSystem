package com.example.demo.form.profile;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfileForm implements Serializable {

	@NotBlank(message = "姓は必須です")
	private String lastName;

	@NotBlank(message = "名は必須です")
	private String firstName;

	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;

	@NotBlank(message = "電話番号は必須です")
	@Size(min = 10, max = 11, message = "電話番号は10桁または11桁で入力してください")
	private String phone;

}
