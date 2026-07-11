package com.example.demo.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.item.OrderHistoryService;

@Controller
public class OrderHistoryController {
	@Autowired
	OrderHistoryService orderHistoryService;

	@GetMapping("/orders")
	public String orderHistory(Model model) {
		model.addAttribute("orderHistories", orderHistoryService.getOrderHistories());
		return "item/ItemOrderHistory";
	}
}
