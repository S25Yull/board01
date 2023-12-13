package com.seoyul.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private String resourcePath = "/upload/**"; // view 에서 접근할 경로
	private String savePath = "file:///Users/choeseoyull/Desktop/board_csy_file/"; // 실제 파일 저장 경로(win)
//	    private String savePath = "file:///Users/사용자이름/springboot_img/"; // 실제 파일 저장 경로(mac)
//		Users/choeseoyull/Desktop/board_csy_file/

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
	}
}
