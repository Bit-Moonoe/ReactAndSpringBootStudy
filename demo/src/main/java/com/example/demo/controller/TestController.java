package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestDTO;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

@RestController
@RequestMapping("test")// 리소스
public class TestController {

	@GetMapping
	public String testController() {
		return "hellddo!~";
	}
	
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello world! testGetMapping";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
		return "Hello id:" + id;
	}
	
	@GetMapping("/testRequestParam") //localhost:8015/test/testRequestParam?id=xxx 로 요청하면 응답
	public String testControllerWithRequestParam(@RequestParam(required = false) int id) {
		return "Hello!!!"+ id;
	}
	
	@GetMapping("/testRequestBody") 
	public String testControllerRequestBody(@RequestBody TestRequestDTO testRequestDTO) {
		return "hello ~ id:" + testRequestDTO.getId() + " message: " + testRequestDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<String>();
		list.add("hello world! I'm responseDTO");
		ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).error("ㅎㅎ").build();
		return res;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity(){
		List<String> list = new ArrayList<String>();
		list.add("Hello World!! I'm ResponseEntity. And you got 400!!");
		ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(res);
	}
}
