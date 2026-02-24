package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.MakerEntity;

public interface MakerService {
	List<MakerEntity> getAllMaker();

	List<ItemEntity> getItemsByMakerId(Long makerId);

	MakerEntity getDetailMaker(Long id);

	void delete(Long id);
}
