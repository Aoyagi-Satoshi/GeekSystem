package com.example.demo.controller.item;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.maker.MakerDetailDto;
import com.example.demo.dto.maker.MakerItemDto;
import com.example.demo.service.item.MakerService;

@RequestMapping("/makers")
@Controller
public class MakerController {

	@Autowired
	private MakerService makerService;

	@GetMapping
	public String makerList(Model model) {

		model.addAttribute("makerlist", makerService.getAllMaker());

		return "item/MakerList";
	}

	@GetMapping("/{id}")
	public String detailMaker(@PathVariable Long id, Model model) {

		MakerDetailDto maker = makerService.getDetailMaker(id);
		List<MakerItemDto> items = makerService.getItemsByMakerId(id);

		model.addAttribute("maker", maker);
		model.addAttribute("items", items);

		return "item/MakerDetail";
	}

	@DeleteMapping("/{id}/delete")
	@Transactional
	public String delete(@PathVariable Long id) {
		makerService.delete(id);

		return "redirect:/makers/list";
	}
}