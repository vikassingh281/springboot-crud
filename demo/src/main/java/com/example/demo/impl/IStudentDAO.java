package com.example.demo.impl;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;

public interface IStudentDAO {
	   void saveStudentInfo(StudentDTO studentDTO);
	   void deleteStudentInfo(String studentId) ;
	   void updateStudentInfo(String id, StudentDTO studentDTO);
	   StudentEntity getStudentInfo(String id);
}
