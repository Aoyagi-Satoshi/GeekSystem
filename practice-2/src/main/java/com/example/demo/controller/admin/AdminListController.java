package com.example.demo.controller.admin;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.AdminEntity;
import com.example.demo.form.admin.AdminEditForm;
import com.example.demo.service.admin.AdminService;

@RequestMapping("/admins")
@Controller
public class AdminListController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/list")
	public String adminList(Model model) {

		model.addAttribute("adminlist", adminService.getAllAdmin());
		model.addAttribute("store", adminService.getStores());

		return "admin/AdminList";
	}

	@GetMapping("/{id}")
	public String detailAdmin(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails user,
			Model model) {

		model.addAttribute("admindetail", adminService.getDetailAdmin(id));
		model.addAttribute("store", adminService.getStores());
		model.addAttribute("role", adminService.getRoles());

		AdminEntity loginAdmin = adminService.getAdminByEmail(user.getUsername());
		model.addAttribute("loginAdmin", loginAdmin);

		return "admin/AdminDetail";
	}

	@GetMapping("/{id}/edit")
	public String editAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("AdminEditForm", adminService.getEdit(id));
		model.addAttribute("stores", adminService.getStores());
		model.addAttribute("roles", adminService.getRoles());
		model.addAttribute("permissions", adminService.getPermissions());
		return "admin/AdminEdit";
	}

	@PutMapping("/update")
	public String updateAdmin(@Validated @ModelAttribute("AdminEditForm") AdminEditForm adminEditForm,
			BindingResult errorResult, Model model) {

		if (errorResult.hasErrors()) {
			model.addAttribute("stores", adminService.getStores());
			model.addAttribute("roles", adminService.getRoles());
			model.addAttribute("permissions", adminService.getPermissions());

			return "admin/AdminEdit";
		}
		adminService.updateAdmin(adminEditForm);
		return "redirect:/admins/list";
	}

	@DeleteMapping("/{id}/delete")
	@Transactional
	public String delete(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails user) {

		AdminEntity loginAdmin = adminService.getAdminByEmail(user.getUsername());

		if (loginAdmin.getPermissionId() == null || loginAdmin.getPermissionId() != 1L) {
			return "redirect:/admins/list";
		}

		adminService.delete(id);
		return "redirect:/admins/list";
	}
}
