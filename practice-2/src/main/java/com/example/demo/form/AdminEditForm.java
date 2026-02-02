package com.example.demo.form;

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
	@NotNull
	private Long storeId;
	@NotBlank
	private String lastName;
	@NotBlank
	private String firstName;
	@Email
	@NotBlank
	private String email;
	@NotNull
	private Long roleId;
	@NotNull
	private Long permissionId;
	@NotBlank
	@Size(min = 10, max = 11)
	private String phone;

}
