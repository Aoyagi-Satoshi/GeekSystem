package com.example.demo.controller;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminEditForm;
import com.example.demo.service.AdminService;

@RequestMapping("/admins")
@Controller
public class AdminListController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/adminList")
	public String adminList(Model model) {
	 
	    model.addAttribute("adminlist", adminService.getAllAdmin());
	    model.addAttribute("store", adminService.getStores());

	    return "AdminList";
	}
	

	@GetMapping("/{id}")
	public String detailAdmin(@PathVariable Long id, Model model) {

		model.addAttribute("admindetail",adminService.getDetailAdmin(id));
		model.addAttribute("store", adminService.getStores());
		model.addAttribute("role", adminService.getRoles());
		return "AdminDetail";
	}

	@GetMapping("/{id}/edit")
	public String editAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("AdminEditForm", adminService.getEdit(id));
		model.addAttribute("stores", adminService.getStores());
		model.addAttribute("roles", adminService.getRoles());
		model.addAttribute("permissions", adminService.getPermissions());
		return "AdminEdit";
	}

	@PostMapping("/update")
	public String contact(@Validated @ModelAttribute("AdminEditForm") AdminEditForm adminEditForm,
			BindingResult errorResult,Model model) {

		if (errorResult.hasErrors()) {
			model.addAttribute("stores", adminService.getStores());
			model.addAttribute("roles", adminService.getRoles());
			model.addAttribute("permissions", adminService.getPermissions());
			
			return "AdminEdit";
		}
		adminService.updateAdmin(adminEditForm);
		return "redirect:/admins/adminList";
	}

	@GetMapping("/{id}/delete")
	@Transactional
	public String delete(@PathVariable Long id) {
		adminService.delete(id);
		return "redirect:/admins/adminList";
	}


}
