package com.example.account.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class WelcomeController {

	@RequestMapping(value = "/")
	public String welcome() {
		System.out.println("WelcomeController|welcome...");
		return "Welcome to Account Service!";
	}
}