package com.example.demo.contoller;

import com.example.demo.dto.CalculatorDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculator")
public class CalcController {
	  
	   @PostMapping(value = "/sum")
	   @ResponseBody
	   public Integer addValue(@RequestBody CalculatorDTO calculatorDTO) {
			 return calculatorDTO.getFirstValue() + calculatorDTO.getSecondValue();
	   }
}
