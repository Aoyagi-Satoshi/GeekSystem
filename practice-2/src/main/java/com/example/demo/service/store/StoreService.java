package com.example.demo.service.store;

import java.util.List;

import com.example.demo.dto.store.StoreListDto;
import com.example.demo.form.store.StoreEditForm;

public interface StoreService {
	List<StoreListDto> getAllStore();

	StoreEditForm getEdit(Long id);

	void updateStore(StoreEditForm storeEditForm);
}
