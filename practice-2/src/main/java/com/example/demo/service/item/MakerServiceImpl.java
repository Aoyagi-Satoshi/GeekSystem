package com.example.demo.service.item;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.example.demo.dto.maker.MakerDetailDto;
import com.example.demo.dto.maker.MakerItemDto;
import com.example.demo.dto.maker.MakerListDto;
import com.example.demo.entity.ItemEntity;
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
		return makerRepository.findAll().stream()
				.map(this::convertToMakerListDto)
				.toList();
	}

	@Override
	public MakerDetailDto getDetailMaker(Long id) {
		MakerEntity maker = makerRepository.findById(id)
				.orElseThrow(() -> new MakerNotFoundException(
						messageSource.getMessage("maker.notfound", null, Locale.getDefault())));

		return convertToMakerDetailDto(maker);
	}

	@Override
	public List<MakerItemDto> getItemsByMakerId(Long makerId) {
		return itemRepository.findByMakerId(makerId).stream()
				.map(this::convertToMakerItemDto)
				.toList();
	}

	@Override
	public void delete(Long id) {
		makerRepository.deleteById(id);
	}

	private MakerListDto convertToMakerListDto(MakerEntity maker) {
		MakerListDto dto = new MakerListDto();
		dto.setId(maker.getId());
		dto.setMakerName(maker.getMakerName());
		dto.setCreatedAt(maker.getCreatedAt());
		return dto;
	}

	private MakerDetailDto convertToMakerDetailDto(MakerEntity maker) {
		MakerDetailDto dto = new MakerDetailDto();
		dto.setId(maker.getId());
		dto.setMakerName(maker.getMakerName());
		return dto;
	}

	private MakerItemDto convertToMakerItemDto(ItemEntity item) {
		MakerItemDto dto = new MakerItemDto();
		dto.setId(item.getId());
		dto.setItemName(item.getItemName());

		if (item.getSmallCategory() != null) {
			dto.setSmallCategoryName(item.getSmallCategory().getSmallName());
		}

		dto.setCreatedAt(item.getCreatedAt());
		return dto;
	}
}
