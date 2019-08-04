package com.example.demo.impl;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;

public interface IStudentWrapper {
	   boolean saveStudentInfo(StudentDTO studentDTO);
	   boolean deleteStudentInfo(String studentId) ;
	   boolean updateStudentInfo(String id, StudentDTO studentDTO);
	   StudentEntity getStudentById(String id);
}
