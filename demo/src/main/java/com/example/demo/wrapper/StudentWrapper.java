package com.example.demo.wrapper;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.impl.IStudentWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

import static com.example.demo.utils.StringUtil.isNullOrEmpty;

@Service
public final class StudentWrapper implements IStudentWrapper {
	   
	   @Autowired
	   StudentDAO studentDAO;
	   
	   private StudentEntity convertDtoToEntity(StudentDTO studentDTO) {
			 ModelMapper mapper = new ModelMapper();
			 return mapper.map(studentDTO, StudentEntity.class);
	   }
	   
	   @Override
	   public boolean saveStudentInfo(StudentDTO studentDTO) {
			 java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			 UUID id = UUID.randomUUID();
			 StudentEntity objStudentEntity = convertDtoToEntity(studentDTO);
			 objStudentEntity.setUniqueId(id.toString());
			 objStudentEntity.setCreatedDate(date.toString());
			 objStudentEntity.setUpdatedDate(date.toString());
			 studentDAO.save(objStudentEntity);
			 return false;
	   }
	   
	   @Override
	   public boolean deleteStudentInfo(String studentId) {
			 studentDAO.deleteById(studentId);
			 return false;
	   }
	   
	   @Override
	   public boolean updateStudentInfo(String id, StudentDTO studentDTO) {
			 boolean dataChanged = false;
			 
			 if (!isNullOrEmpty(studentDTO.getFirstName())
				|| !isNullOrEmpty(studentDTO.getLastName())
				|| !isNullOrEmpty(studentDTO.getDepartment())) {

				    StudentEntity objStudentEntity = studentDAO.findbyId(id);
				  
				    if (objStudentEntity != null) {
						  ModelMapper mapper = new ModelMapper();
						  StudentEntity updatedInfo = mapper.map(studentDTO, StudentEntity.class);
						  
						  if (!isNullOrEmpty(updatedInfo.getFirstName())) {
								objStudentEntity.setFirstName(updatedInfo.getFirstName());
								dataChanged = true;
						  }
						  
						  if (!isNullOrEmpty(updatedInfo.getLastName())) {
								objStudentEntity.setLastName(updatedInfo.getLastName());
								dataChanged = true;
						  }
						  
						  if (!isNullOrEmpty(updatedInfo.getDepartment())) {
								objStudentEntity.setDepartment(updatedInfo.getDepartment());
								dataChanged = true;
						  }
						  if (dataChanged) {
								java.sql.Date updatedDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
								objStudentEntity.setUpdatedDate(updatedDate.toString());
								studentDAO.update(objStudentEntity);
								return true;
						  }
				    }
			 }
			 else {
				    throw new IllegalArgumentException("No data Changed for id '" + id + "'");
			 }
			 return false;
	   }
	   
	   
	   @Override
	   public StudentEntity getStudentById(String id) {
			 return studentDAO.getStudentById(id);
	   }
}
