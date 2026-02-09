package com.example.demo.service;

import com.example.demo.entity.AdminEntity;
import com.example.demo.form.ProfileForm;

public interface ProfileService {

	AdminEntity getProfileByEmail(String email);

	public void updateProfile(String email, ProfileForm profileForm);
}
