package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component(value = "calcModel")
@Getter
@Setter
public class CalculatorDTO {
	   
	   private Integer firstValue;
	   private Integer secondValue;
	   
}
