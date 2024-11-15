package com.uhaiin.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipsRunner implements CommandLineRunner {

	@Override
	public void run(String[] args) {
		System.out.println("------------------- tips -------------------");
		System.out.println("1.请修改项目实际名称");
		System.out.println("2.基于SpringBoot 3，需要 jdk 17+");
		System.out.println("3.项目内置常用模块，请及时删除不需要的模块代码");
		System.out.println("--------------------------------------------");
	}
}