package com.example.demo.dao;

import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAO {
	   
	   @Autowired
	   private StudentRepository studentRepository;
	   
	   public void save(StudentEntity studentInfo) {
			 studentRepository.save(studentInfo);
	   }
	   
	   public void deleteById(String studentId) {
			 studentRepository.deleteById(studentId);
	   }
	   
	   public void update(StudentEntity objStudentEntity) {
			 studentRepository.save(objStudentEntity);
	   }
	   
	   public StudentEntity getStudentById(String id) {
			 return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No data found for id {0}", id)));
	   }
	   
	   public List<StudentEntity> getAllStudentInfo() {
			 return studentRepository.findAll();
	   }
	   
	   public StudentEntity findbyId(String id) {
			 return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No data found for id {0}", id)));
	   }
	   
}
