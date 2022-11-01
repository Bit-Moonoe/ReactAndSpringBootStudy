package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//모든 경로에 대해
		registry.addMapping("/**")
			//Origin(프론트엔드)이 http:localhost:8015에 대해.
		.allowedOrigins("http://localhost:3000")
			//GET,POST,PUT,PATCH,DELETE,OPTIONS 메서드를 허용한다.
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
	}
}
