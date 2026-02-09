package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StoreEntity;
import com.example.demo.form.StoreEditForm;
import com.example.demo.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	StoreRepository storeRepository;

	@Override
	public List<StoreEntity> getAllStore() {
		return storeRepository.findAll();
	}
	@Override
	public StoreEditForm getEdit(Long id) {
		StoreEntity store = storeRepository.findById(id).orElse(null);
		StoreEditForm edit = new StoreEditForm();
		edit.setId(store.getId());
		edit.setStoreName(store.getStoreName());
		edit.setAddress(store.getAddress());

		return edit;
	}
	public void updateStore(StoreEditForm storeEditForm) {
		StoreEntity store = storeRepository.findById(storeEditForm.getId())
				.orElseThrow(() -> new RuntimeException("IDが見つかりませんでした"));
		store.setStoreName(storeEditForm.getStoreName());
		store.setAddress(storeEditForm.getAddress());


		storeRepository.save(store);
	}
}
