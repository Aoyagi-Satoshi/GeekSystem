package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemEntity;
import com.example.demo.repository.ItemRepository;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<ItemEntity> getAllItem() {
		return itemRepository.findAll();
	}
}
