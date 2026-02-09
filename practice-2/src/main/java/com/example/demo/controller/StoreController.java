package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.StoreEditForm;
import com.example.demo.service.StoreService;

@Controller
public class StoreController {
	@Autowired StoreService storeService;
	
	@GetMapping("/storeList")
	public String storeList(Model model) {
	 
	    model.addAttribute("stores", storeService.getAllStore());

	    return "StoreList";
	}
	@GetMapping("/{id}/edit")
	public String editStore(@PathVariable Long id, Model model) {
	    model.addAttribute("StoreEditForm", storeService.getEdit(id));
	    return "StoreEdit";
	}

	@PostMapping("/store/confirm")
	public String confirmStore(
	        @Validated @ModelAttribute("StoreEditForm") StoreEditForm storeEditForm,
	        BindingResult result) {

	    if (result.hasErrors()) {
	        return "StoreEdit";
	    }
	    return "StoreConfirmation";
	}

	@PostMapping("/store/update")
	public String updateStore(
	        @ModelAttribute("StoreEditForm") StoreEditForm storeEditForm) {

	    storeService.updateStore(storeEditForm);
	    return "redirect:/storeList";
	}
}
