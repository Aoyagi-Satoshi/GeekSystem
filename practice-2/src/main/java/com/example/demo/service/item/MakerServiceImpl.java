package com.example.demo.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.MakerEntity;
import com.example.demo.repository.item.ItemRepository;
import com.example.demo.repository.item.MakerRepository;

@Service
public class MakerServiceImpl implements MakerService {
	@Autowired
	private MakerRepository makerRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<MakerEntity> getAllMaker() {
		return makerRepository.findAll();
	}

	@Override
	public MakerEntity getDetailMaker(Long id) {
		return makerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("管理者が見つかりません"));
	}

	@Override
	public List<ItemEntity> getItemsByMakerId(Long makerId) {
		return itemRepository.findByMakerId(makerId);
	}

	@Override
	public void delete(Long id) {
		makerRepository.deleteById(id);
	}
}
