package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
	   private String firstName;
	   private String lastName;
	   private String department;
	   private String createdDate;
	   private String updatedDate;
}
