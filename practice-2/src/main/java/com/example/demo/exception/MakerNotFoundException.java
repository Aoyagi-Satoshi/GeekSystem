package com.example.demo.exception;

public class MakerNotFoundException extends RuntimeException {
	public MakerNotFoundException(String message) {
		super(message);
	}
}
