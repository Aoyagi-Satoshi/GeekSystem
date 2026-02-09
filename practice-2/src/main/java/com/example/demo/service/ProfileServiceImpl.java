package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminEntity;
import com.example.demo.form.ProfileForm;
import com.example.demo.repository.AdminRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public AdminEntity getProfileByEmail(String email) {
		return adminRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("ログインユーザーが見つかりません"));
	}

	@Override
	public void updateProfile(String email, ProfileForm profileForm) {

		AdminEntity user = adminRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));

		user.setLastName(profileForm.getLastName());
		user.setFirstName(profileForm.getFirstName());
		user.setEmail(profileForm.getEmail());
		user.setPhone(profileForm.getPhone());

		adminRepository.save(user);
	}
}
