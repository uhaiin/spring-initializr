package com.uhaiin.service.impl;

import org.springframework.stereotype.Service;

import com.uhaiin.service.YourService;

@Service
public class YourServiceImpl implements YourService {
	@Override
	public String sayHello() {
		return "hello world";
	}
}
