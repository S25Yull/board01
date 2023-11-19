package com.seoyul.home.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {
	
	//메인 페이지
@GetMapping("/")
	public String home() {
	log.info("fgdg");
		return "home/home"; //home폴더 안에 있는 home.html
		
	}
@GetMapping("/main")
public String main() {
	return "member/main"; //home폴더 안에 있는 home.html
}
	
}