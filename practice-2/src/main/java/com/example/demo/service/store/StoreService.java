package com.example.demo.service.store;

import java.util.List;

import com.example.demo.entity.StoreEntity;
import com.example.demo.form.store.StoreEditForm;

public interface StoreService {
	List<StoreEntity> getAllStore();

	StoreEditForm getEdit(Long id);

	void updateStore(StoreEditForm storeEditForm);
}
