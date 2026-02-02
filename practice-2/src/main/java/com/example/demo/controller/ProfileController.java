package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.AdminEntity;
import com.example.demo.form.ProfileForm;
import com.example.demo.service.ProfileService;

@RequestMapping("/profile")
@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping
	public String profile(
			@AuthenticationPrincipal UserDetails user,
			Model model) {

		String email = user.getUsername();
		AdminEntity admin = profileService.getProfileByEmail(email);
		model.addAttribute("profile", admin);
		return "Profile";
	}

	@GetMapping("/edit")
	public String edit(Model model,
			@AuthenticationPrincipal UserDetails user) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		ProfileForm form = new ProfileForm();
		form.setLastName(admin.getLastName());
		form.setFirstName(admin.getFirstName());
		form.setEmail(admin.getEmail());
		form.setPhone(admin.getPhone());

		model.addAttribute("profileForm", form);
		return "ProfileEdit";
	}

	@PostMapping("/edit")
	public String update(
			@Validated ProfileForm profileForm,
			BindingResult result,
			@AuthenticationPrincipal UserDetails user) {

		if (result.hasErrors()) {
			return "ProfileEdit";
		}

		profileService.updateProfile(user.getUsername(), profileForm);
		return "redirect:/profile";
	}
}
