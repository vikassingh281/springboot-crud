package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
	   
	   @JsonProperty("firstName")
	   private String FirstName;
	   
	   @JsonProperty("firstName")
	   private String LastName;
	   
	   @JsonProperty("firstName")
	   private String Department;
	   
}
