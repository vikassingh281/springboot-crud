package com.example.demo.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/controller")
@RestController
public class HelloWorldController {
	   
	   @GetMapping(value = "/helloworld")
	   @ResponseBody
	   public String getHello() {
			 return "Hello World";
	   }
	   
}
