package com.example.demo.controller.item;

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

import com.example.demo.dto.item.OrderItemDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.form.item.OrderForm;
import com.example.demo.service.item.OrderService;
import com.example.demo.service.profile.ProfileService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProfileService profileService;

	@GetMapping("/item/{id}/haccyu")
	public String showOrderPage(@PathVariable Long id,
			@AuthenticationPrincipal UserDetails user,
			HttpServletRequest request,
			Model model) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		OrderItemDto item = orderService.getOrderItem(admin.getStoreId(), id);

		HttpSession session = request.getSession();
		OrderForm form = (OrderForm) session.getAttribute("orderForm");

		if (form == null || form.getItemId() == null || !form.getItemId().equals(id)) {
			form = new OrderForm();
			form.setItemId(id);
		}

		model.addAttribute("item", item);
		model.addAttribute("orderForm", form);

		return "item/itemOrder";
	}

	@PostMapping("/item/order/confirm")
	public String confirmOrder(@Validated @ModelAttribute("orderForm") OrderForm orderForm,
			BindingResult result,
			HttpServletRequest request,
			@AuthenticationPrincipal UserDetails user,
			Model model) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		OrderItemDto item = orderService.getOrderItem(admin.getStoreId(), orderForm.getItemId());

		if (result.hasErrors()) {
			model.addAttribute("item", item);
			model.addAttribute("admin", admin);
			return "item/itemOrder";
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
			return "redirect:/items";
		}

		OrderItemDto item = orderService.getOrderItem(admin.getStoreId(), orderForm.getItemId());

		model.addAttribute("orderForm", orderForm);
		model.addAttribute("item", item);

		return "item/itemOrderConfirmation";
	}

	@PostMapping("/item/order")
	public String orderItem(HttpServletRequest request,
			@AuthenticationPrincipal UserDetails user) {

		AdminEntity admin = profileService.getProfileByEmail(user.getUsername());

		HttpSession session = request.getSession();
		OrderForm orderForm = (OrderForm) session.getAttribute("orderForm");

		if (orderForm == null) {
			return "redirect:/itemList";
		}

		orderService.orderItem(
				admin.getStoreId(),
				orderForm.getItemId(),
				orderForm.getOrderQuantity(),
				admin);

		session.removeAttribute("orderForm");

		return "redirect:/items";
	}
}