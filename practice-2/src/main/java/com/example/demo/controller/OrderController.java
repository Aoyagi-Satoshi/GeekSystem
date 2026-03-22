package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.StoreItemEntity;
import com.example.demo.form.OrderForm;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProfileService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProfileService profileService;

	@GetMapping("/item/{id}/haccyu")
	public String showOrderPage(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails user,
			HttpServletRequest request,
			Model model) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		ItemEntity item = itemService.findById(id);
		StoreItemEntity storeItem = orderService.findByStoreAndItem(admin.getStoreId(), id);

		HttpSession session = request.getSession();
		OrderForm form = (OrderForm) session.getAttribute("orderForm");

		if (form == null || form.getItemId() == null || !form.getItemId().equals(id)) {
			form = new OrderForm();
			form.setItemId(id);
		}

		model.addAttribute("item", item);
		model.addAttribute("storeItem", storeItem);
		model.addAttribute("orderForm", form);
		model.addAttribute("admin", admin);

		return "itemOrder";
	}

	@PostMapping("/item/order/confirm")
	public String confirmOrder(@Validated @ModelAttribute("orderForm") OrderForm orderForm,
			BindingResult result,
			HttpServletRequest request,
			@AuthenticationPrincipal UserDetails user,
			Model model) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		ItemEntity item = itemService.findById(orderForm.getItemId());
		StoreItemEntity storeItem = orderService.findByStoreAndItem(admin.getStoreId(), orderForm.getItemId());

		if (result.hasErrors()) {
			model.addAttribute("item", item);
			model.addAttribute("storeItem", storeItem);
			model.addAttribute("orderForm", orderForm);
			model.addAttribute("admin", admin);
			return "itemOrder";
		}

		HttpSession session = request.getSession();
		session.setAttribute("orderForm", orderForm);

		return "redirect:/item/order/confirm";
	}

	@GetMapping("/item/order/confirm")
	public String showConfirm(HttpServletRequest request,
			@AuthenticationPrincipal UserDetails user,
			Model model) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		HttpSession session = request.getSession();
		OrderForm orderForm = (OrderForm) session.getAttribute("orderForm");

		if (orderForm == null) {
			return "redirect:/itemList";
		}

		ItemEntity item = itemService.findById(orderForm.getItemId());
		StoreItemEntity storeItem = orderService.findByStoreAndItem(admin.getStoreId(), orderForm.getItemId());

		model.addAttribute("orderForm", orderForm);
		model.addAttribute("item", item);
		model.addAttribute("storeItem", storeItem);
		model.addAttribute("admin", admin);

		return "itemOrderConfirmation";
	}

	@PostMapping("/item/order")
	public String orderItem(HttpServletRequest request,
			@AuthenticationPrincipal UserDetails user,
			RedirectAttributes redirectAttributes) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		HttpSession session = request.getSession();
		OrderForm orderForm = (OrderForm) session.getAttribute("orderForm");

		if (orderForm == null) {
			return "redirect:/itemList";
		}

		orderService.orderItem(admin.getStoreId(), orderForm.getItemId(), orderForm.getOrderQuantity());

		session.removeAttribute("orderForm");

		return "redirect:/itemList";
	}
}