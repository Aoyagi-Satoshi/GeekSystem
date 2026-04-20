package com.example.demo.form.admin;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminForm implements Serializable {

	@NotNull(message = "{admin.storeId.required}")
	private Long storeId;

	@NotBlank(message = "{admin.lastName.required}")
	private String lastName;

	@NotBlank(message = "{admin.firstName.required}")
	private String firstName;

	@NotBlank(message = "{admin.email.required}")
	@Email(message = "{admin.email.invalid}")
	private String email;

	@NotNull(message = "{admin.roleId.required}")
	private Long roleId;

	@NotNull(message = "{admin.permissionId.required}")
	private Long permissionId;

	@NotBlank(message = "{admin.phone.required}")
	@Size(min = 10, max = 11, message = "{admin.phone.size}")
	private String phone;

	@NotBlank(message = "{admin.password.required}")
	private String password;
}
