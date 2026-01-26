package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;
@RequestMapping("/admins")
@Controller
public class AdminRegisterController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/new")
	public String admin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		model.addAttribute("stores", adminService.getStores());
		model.addAttribute("roles", adminService.getRoles());
		model.addAttribute("permissions", adminService.getPermissions());

		return "AdminRegister";
	}

	@PostMapping("/new")
	public String admin(@Validated  AdminForm adminForm, BindingResult errorResult,
			HttpServletRequest request, Model model) {

		if (errorResult.hasErrors()) {
			model.addAttribute("stores", adminService.getStores());
			model.addAttribute("roles", adminService.getRoles());
			model.addAttribute("permissions", adminService.getPermissions());
			return "AdminRegister";
		}

		HttpSession session = request.getSession();
		session.setAttribute("adminForm", adminForm);

		return "redirect:/admins/confirm";
	}

	@GetMapping("/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		   System.out.println("confirm に来た");

		    HttpSession session = request.getSession();

		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
	    model.addAttribute("adminForm", adminForm);
	    model.addAttribute("store",
	        adminService.getStoreById(adminForm.getStoreId()));
	    model.addAttribute("role",
	        adminService.getRoleById(adminForm.getRoleId()));
	    model.addAttribute("permission",
	        adminService.getPermissionById(adminForm.getPermissionId()));

		return "AdminConfirmation";

	}

	@PostMapping("/register")
	public String register(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");

		adminService.saveAdmin(adminForm);

		return "redirect:/admins/adminList";
	}


}
