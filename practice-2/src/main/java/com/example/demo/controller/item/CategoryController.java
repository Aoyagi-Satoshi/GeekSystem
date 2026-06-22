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

	@GetMapping("/categories/middles/{id}")
	public String middleCategoryList(Model model) {

		model.addAttribute(
				"categories",
				categoryService.getAllMiddleList());

		return "item/MiddleCategoryList";
	}

	@GetMapping("/categories/smalls/{id}")
	public String smallCategoryList(Model model) {

		model.addAttribute(
				"categories",
				categoryService.getAllSmallList());

		return "item/SmallCategoryList";
	}

	@GetMapping("/categories/smallDetail/{id}")
	public String smallCategoryDetail(@PathVariable Long id, Model model) {

		model.addAttribute("items", categoryService.getSmallCategoryDetail(id));

		return "item/SmallCategoryDetail";
	}
}
