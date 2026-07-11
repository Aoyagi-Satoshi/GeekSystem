package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admins")
	public String admin(Model model) {
		model.addAttribute("adminForm", new AdminForm());
		model.addAttribute("stores", adminService.getStores());
		model.addAttribute("roles", adminService.getRoles());
		model.addAttribute("permissions", adminService.getPermissions());

		return "admins";
	}

	@PostMapping("/admins")
	public String admin(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult,
			HttpServletRequest request, Model model) {

		if (errorResult.hasErrors()) {
			model.addAttribute("stores", adminService.getStores());
			model.addAttribute("roles", adminService.getRoles());
			model.addAttribute("permissions", adminService.getPermissions());
			return "admins";
		}

		HttpSession session = request.getSession();
		session.setAttribute("adminForm", adminForm);

		return "redirect:/admins/confirm";
	}

	@GetMapping("/admins/confirm")
	public String confirm(Model model, HttpServletRequest request) {
		   System.out.println("confirm に来た");

		    HttpSession session = request.getSession();
		    System.out.println( session);

		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
	    model.addAttribute("adminForm", adminForm);
	    model.addAttribute("store",
	        adminService.getStoreById(adminForm.getStoreId()));
	    model.addAttribute("role",
	        adminService.getRoleById(adminForm.getRoleId()));
	    model.addAttribute("permission",
	        adminService.getPermissionById(adminForm.getPermissionId()));

		return "confirmation";

	}

	@PostMapping("/admins/register")
	public String register(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");

		adminService.saveAdmin(adminForm);

		return "redirect:/complete";
	}

	@GetMapping("/complete")
	public String complete(Model model, HttpServletRequest request) {

		if (request.getSession(false) == null) {
			return "redirect:/admins";
		}

		HttpSession session = request.getSession();
		AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
		model.addAttribute("adminForm", adminForm);

		session.invalidate();

		return "completion";
	}
}
