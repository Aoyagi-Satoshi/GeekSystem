package com.example.demo.service.store;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.dto.store.StoreListDto;
import com.example.demo.entity.StoreEntity;
import com.example.demo.exception.StoreNotFoundException;
import com.example.demo.form.store.StoreEditForm;
import com.example.demo.repository.store.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	StoreRepository storeRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public List<StoreListDto> getAllStore() {
		return storeRepository.findAll().stream()
				.map(this::convertToStoreListDto)
				.toList();
	}

	@Override
	public StoreEditForm getEdit(Long id) {
		StoreEntity store = storeRepository.findById(id)
				.orElseThrow(() -> new StoreNotFoundException(
						messageSource.getMessage("store.notfound", null, Locale.getDefault())));

		return convertToStoreEditForm(store);
	}

	@Override
	public void updateStore(StoreEditForm storeEditForm) {
		StoreEntity store = storeRepository.findById(storeEditForm.getId())
				.orElseThrow(() -> new StoreNotFoundException(
						messageSource.getMessage("store.notfound", null, Locale.getDefault())));

		store.setStoreName(storeEditForm.getStoreName());
		store.setAddress(storeEditForm.getAddress());

		storeRepository.save(store);
	}

	private StoreListDto convertToStoreListDto(StoreEntity store) {
		StoreListDto dto = new StoreListDto();
		dto.setId(store.getId());
		dto.setStoreName(store.getStoreName());
		dto.setAddress(store.getAddress());
		return dto;
	}

	private StoreEditForm convertToStoreEditForm(StoreEntity store) {
		StoreEditForm edit = new StoreEditForm();
		edit.setId(store.getId());
		edit.setStoreName(store.getStoreName());
		edit.setAddress(store.getAddress());
		return edit;
	}
}
