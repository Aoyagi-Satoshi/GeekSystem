package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.StoreEntity;
import com.example.demo.form.StoreEditForm;

public interface StoreService {
	List<StoreEntity> getAllStore();

	StoreEditForm getEdit(Long id);
	void updateStore(StoreEditForm storeEditForm);
}
