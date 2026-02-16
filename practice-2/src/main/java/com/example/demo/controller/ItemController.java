package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@GetMapping("/itemList")
		public String itemList(Model model) {
			 model.addAttribute("item",itemService.getAllItem());
			
			return "ItemList";
			
		}
		 

}
