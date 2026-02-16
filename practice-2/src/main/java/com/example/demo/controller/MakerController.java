package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.MakerService;

@Controller
public class MakerController {
	@Autowired
	private MakerService makerService;

	@GetMapping("/makerList")
	public String makerList(Model model) {
	 
	    model.addAttribute("makerlist", makerService.getAllMaker());
	   
	    return "MakerList";
	}
}
