package com.example.demo.exception;

public class StoreItemNotFoundException extends RuntimeException {

	public StoreItemNotFoundException(String message) {
		super(message);
	}
}