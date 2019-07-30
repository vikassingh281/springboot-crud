package com.example.demo.dao;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.impl.IStudentDAO;
import com.example.demo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static com.example.demo.utils.Utility.isNullOrEmpty;


@Component
public class StudentDAO implements IStudentDAO {
	   
	   @Autowired
	   private StudentRepository studentRepository;
	   
	   public void saveStudentInfo(StudentDTO studentDTO) {
			 java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			 UUID id = UUID.randomUUID();
			 StudentEntity studentInfo = convertDtoToEntity(studentDTO);
			 studentInfo.setUniqueId(id.toString());
			 studentInfo.setCreatedDate(date.toString());
			 studentInfo.setUpdatedDate(date.toString());
			 studentRepository.save(studentInfo);
	   }
	   
	   public void deleteStudentInfo(String studentId) {
			 studentRepository.deleteById(studentId);
	   }
	   
	   public void updateStudentInfo(String id, StudentDTO studentDTO) {
			 boolean dataChanged = false;
			 StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data found for id " + id));
			 if (studentEntity != null) {
				    ModelMapper mapper = new ModelMapper();
				    StudentEntity updatedInfo = mapper.map(studentDTO, StudentEntity.class);
				    String firstName = updatedInfo.getFirstName();
				    if (!isNullOrEmpty(firstName)) {
						  studentEntity.setFirstName(firstName);
						  dataChanged = true;
				    }
				    
				    String lastName = updatedInfo.getLastName();
				    if (!isNullOrEmpty(lastName)) {
						  studentEntity.setLastName(lastName);
						  dataChanged = true;
				    }
				    
				    String department = updatedInfo.getDepartment();
				    if (!isNullOrEmpty(department)) {
						  studentEntity.setDepartment(department);
						  dataChanged = true;
				    }
				    if (dataChanged) {
						  java.sql.Date updatedDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						  studentEntity.setUpdatedDate(updatedDate.toString());
						  studentRepository.save(studentEntity);
				    } else {
						  throw new IllegalArgumentException("No data Changed for id '" + id + "'");
				    }
			 }
	   }
	   
	   public StudentEntity getStudentInfo(String id) {
			 return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No data found for id " + id));
	   }
	   
	   public List<StudentEntity> getAllStudentInfo() {
			 return studentRepository.findAll();
	   }
	   
	   private StudentEntity convertDtoToEntity(StudentDTO studentDTO) {
			 ModelMapper mapper = new ModelMapper();
			 return mapper.map(studentDTO, StudentEntity.class);
	   }
	   
}
