package com.uhaiin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uhaiin.service.YourService;

@RestController
public class YourController {

	private final YourService yourService;

	@Autowired
	public YourController(YourService yourService) {
		this.yourService = yourService;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return yourService.sayHello();
	}
}
