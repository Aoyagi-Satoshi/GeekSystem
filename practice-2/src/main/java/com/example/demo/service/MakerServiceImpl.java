package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MakerEntity;
import com.example.demo.repository.MakerRepository;

@Service
public class MakerServiceImpl implements MakerService {
	@Autowired
	MakerRepository makerRepository;

	@Override
	public List<MakerEntity> getAllMaker() {
		return makerRepository.findAll();
	}
}
