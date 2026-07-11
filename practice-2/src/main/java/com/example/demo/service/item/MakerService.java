package com.example.demo.service.item;

import java.util.List;

import com.example.demo.dto.maker.MakerDetailDto;
import com.example.demo.dto.maker.MakerItemDto;
import com.example.demo.dto.maker.MakerListDto;

public interface MakerService {
	List<MakerListDto> getAllMaker();

	List<MakerItemDto> getItemsByMakerId(Long makerId);

	MakerDetailDto getDetailMaker(Long id);

	void delete(Long id);
}
