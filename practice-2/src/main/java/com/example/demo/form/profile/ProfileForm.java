package com.example.demo.form.profile;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfileForm implements Serializable {

	@NotBlank(message = "{profile.lastName.required}")
	private String lastName;

	@NotBlank(message = "{profile.firstName.required}")
	private String firstName;

	@NotBlank(message = "{profile.email.required}")
	@Email(message = "{profile.email.invalid}")
	private String email;

	@NotBlank(message = "{profile.phone.required}")
	@Size(min = 10, max = 11, message = "{profile.phone.size}")
	private String phone;

}
