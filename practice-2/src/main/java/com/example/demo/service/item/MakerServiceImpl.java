package com.example.demo.service.item;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.dto.maker.MakerDetailDto;
import com.example.demo.dto.maker.MakerItemDto;
import com.example.demo.dto.maker.MakerListDto;
import com.example.demo.entity.MakerEntity;
import com.example.demo.exception.MakerNotFoundException;
import com.example.demo.repository.item.ItemRepository;
import com.example.demo.repository.item.MakerRepository;

@Service
public class MakerServiceImpl implements MakerService {
	@Autowired
	private MakerRepository makerRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private MessageSource messageSource;

	@Override
	public List<MakerListDto> getAllMaker() {
		return makerRepository.findAll().stream().map(maker -> {
			MakerListDto dto = new MakerListDto();
			dto.setId(maker.getId());
			dto.setMakerName(maker.getMakerName());
			dto.setCreatedAt(maker.getCreatedAt());
			return dto;
		}).toList();
	}

	@Override
	public MakerDetailDto getDetailMaker(Long id) {
		MakerEntity maker = makerRepository.findById(id)
				.orElseThrow(() -> new MakerNotFoundException(
						messageSource.getMessage("maker.notfound", null, Locale.getDefault())));
		MakerDetailDto dto = new MakerDetailDto();
		dto.setId(maker.getId());
		dto.setMakerName(maker.getMakerName());
		return dto;
	}

	@Override
	public List<MakerItemDto> getItemsByMakerId(Long makerId) {
		return itemRepository.findByMakerId(makerId).stream().map(item -> {
			MakerItemDto dto = new MakerItemDto();
			dto.setId(item.getId());
			dto.setItemName(item.getItemName());
			dto.setSmallCategoryName(item.getSmallCategory().getSmallName());
			dto.setCreatedAt(item.getCreatedAt());
			return dto;
		}).toList();
	}

	@Override
	public void delete(Long id) {
		makerRepository.deleteById(id);
	}
}
