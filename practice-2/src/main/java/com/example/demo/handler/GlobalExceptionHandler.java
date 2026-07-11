package com.example.demo.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.AdminNotFoundException;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.StoreItemNotFoundException;
import com.example.demo.exception.StoreNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({
			AdminNotFoundException.class,
			ItemNotFoundException.class,
			StoreNotFoundException.class,
			StoreItemNotFoundException.class
	})
	public String handleNotFound(RuntimeException e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		return "error/error";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("errorMessage", "システムエラーが発生しました");
		return "error/error";
	}
}
