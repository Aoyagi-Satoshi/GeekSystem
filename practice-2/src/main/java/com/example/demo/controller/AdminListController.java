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

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@RequestMapping("/admins")
@Controller
public class AdminListController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/adminList")
	public String adminList(Model model) {
		model.addAttribute("adminlist", adminService.getAllAdmin());
		model.addAttribute("stores", adminService.getStores());
		return "AdminList";
	}

	@GetMapping("/adminList/{id}")
	public String detailAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("admindetail",adminService.getDetailAdmin(id));
		model.addAttribute("stores", adminService.getStores());
		model.addAttribute("roles", adminService.getRoles());
		return "AdminDetail";
	}

	@GetMapping("/{id}/edit")
	public String editAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("adminForm", adminService.getEdit(id));
		return "AdminEdit";
	}

	@PostMapping("/update")
	public String contact(@Validated @ModelAttribute("AdminForm") AdminForm adminForm,
			BindingResult errorResult) {

		if (errorResult.hasErrors()) {
			return "AdminEdit";
		}
		adminService.updateAdmin(adminForm);
		return "redirect:/admin/contacts";
	}

	@GetMapping("/{id}/delete")
	@Transactional
	public String delete(@PathVariable Long id) {
		adminService.delete(id);
		return "redirect:/admin/contacts";
	}

	@GetMapping("/signup")
	public String admin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@Validated AdminForm adminForm, BindingResult errorResult) {

		if (errorResult.hasErrors()) {
			return "signup";
		}
		adminService.saveAdmin(adminForm);
		return "redirect:/admin/signin";
	}

	@GetMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		return "signin";
	}
}
