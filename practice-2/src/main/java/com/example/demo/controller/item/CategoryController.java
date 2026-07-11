package com.example.demo.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.item.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/categories")
	public String largeCategoryList(Model model) {

		model.addAttribute(
				"categories",
				categoryService.getAllLargeList());

		return "item/LargeCategoryList";
	}

	@GetMapping("/categories/middles/{largeCategoryId}")
	public String middleList(@PathVariable Long largeCategoryId, Model model) {
		model.addAttribute("middleCategories",
				categoryService.getMiddleListByLargeId(largeCategoryId));

		return "item/MiddleCategoryList";
	}

	@GetMapping("/categories/smalls/{middleCategoryId}")
	public String smallList(@PathVariable Long middleCategoryId, Model model) {

		model.addAttribute("smallCategories",
				categoryService.getSmallListByMiddleId(middleCategoryId));

		Long largeCategoryId = categoryService.getLargeCategoryIdByMiddleCategoryId(middleCategoryId);

		model.addAttribute("largeCategoryId", largeCategoryId);

		return "item/SmallCategoryList";
	}

	@GetMapping("/categories/smallDetail/{smallCategoryId}")
	public String smallCategoryDetail(
			@PathVariable Long smallCategoryId,
			Model model) {

		model.addAttribute("items",
				categoryService.getSmallCategoryDetail(smallCategoryId));

		Long middleCategoryId = categoryService.getMiddleCategoryIdBySmallCategoryId(smallCategoryId);

		model.addAttribute("middleCategoryId", middleCategoryId);

		return "item/SmallCategoryDetail";
	}
}
