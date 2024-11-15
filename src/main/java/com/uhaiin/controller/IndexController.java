package com.uhaiin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	/**
	 * 访问首页
	 *
	 * @return 首页
	 */
	@GetMapping({ "/", "/index" })
	public String index() {
		return "index";
	}
}
