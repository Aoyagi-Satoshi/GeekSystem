package com.example.demo.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.item.ItemListDto;
import com.example.demo.service.item.CategoryService;
import com.example.demo.service.item.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/items")
	public String getItemList(
			Model model,
			@RequestParam(required = false) String itemName,
			@RequestParam(required = false) Long largeCategoryId,
			@RequestParam(required = false) Long middleCategoryId,
			@RequestParam(required = false) Long smallCategoryId,
			@PageableDefault(size = 5) Pageable pageable) {

		Page<ItemListDto> itemsPage = itemService.searchItems(
				itemName,
				largeCategoryId,
				middleCategoryId,
				smallCategoryId,
				pageable);

		model.addAttribute("page", itemsPage);
		model.addAttribute("itemlist", itemsPage.getContent());

		model.addAttribute("itemName", itemName);
		model.addAttribute("largeCategoryId", largeCategoryId);
		model.addAttribute("middleCategoryId", middleCategoryId);
		model.addAttribute("smallCategoryId", smallCategoryId);

		model.addAttribute("largeCategoryList", categoryService.getAllLargeCategories());
		model.addAttribute("middleCategoryList", categoryService.getAllMiddleCategories());
		model.addAttribute("smallCategoryList", categoryService.getAllSmallCategories());

		return "item/ItemList";
	}
}