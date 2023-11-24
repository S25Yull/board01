package com.seoyul.home.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class HomeController {

	// 메인 페이지
	@GetMapping("/")
	public String home() {
		
		return "home/home"; // home폴더 안에 있는 home.html
	}
	
	//보드 페이지
	@GetMapping("/main")
	public String board() {
		
		return "member/main"; // home폴더 안에 있는 home.html
	}
	
}