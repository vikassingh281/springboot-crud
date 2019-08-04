package com.example.demo.contoller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.wrapper.StudentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
	   
	   private static String errorMessage = "No entity with id '{0}' found";
	   private String responseMessage;
	   private HttpStatus responseStatusCode;
	   private ResponseDTO responseDTO;

	   @Autowired
	   private StudentWrapper objStudentWrapper;
	   
	   @PostMapping(value = "/1.0/save")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> save(@RequestBody StudentDTO studentDTO) {
			 try {
				    objStudentWrapper.saveStudentInfo(studentDTO);
				    responseStatusCode = HttpStatus.OK;
				    responseMessage = "Data Saved Successfully";
			 } catch (Exception ex) {
				    responseMessage = ex.getMessage();
				    responseStatusCode = HttpStatus.NOT_FOUND;
			 }
			 responseDTO = new ResponseDTO(responseMessage, responseStatusCode, responseStatusCode.value());
			 return ResponseEntity.status(responseStatusCode).body(responseDTO);
	   }
	   
	   @GetMapping(value = "/1.0/getstudentinfo")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> getStudentInfo(@RequestParam(name = "id") String id) {
			 StudentEntity studentEntity = null;
			 try {
				    studentEntity = objStudentWrapper.getStudentById(id);
				    responseStatusCode = HttpStatus.OK;
				    responseMessage = "Data Found successfully";
			 } catch (ResourceNotFoundException ex) {
				    responseMessage = String.format(errorMessage, id);
				    responseStatusCode = HttpStatus.NOT_FOUND;
			 } catch (Exception ex) {
				    responseMessage = ex.getMessage();
				    responseStatusCode = HttpStatus.PRECONDITION_FAILED;
			 }
			 responseDTO = new ResponseDTO(responseMessage, responseStatusCode, responseStatusCode.value(), studentEntity);
			 return ResponseEntity.status(responseStatusCode).body(responseDTO);
	   }
	   
	   @DeleteMapping(value = "/1.0/delete")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> deleteStudentInfo(@RequestParam(name = "id") String id) {
			 try {
				    objStudentWrapper.deleteStudentInfo(id);
				    responseStatusCode = HttpStatus.OK;
				    responseMessage = "Data Delete Successfully";
			 } catch (EmptyResultDataAccessException ex) {
				    responseStatusCode = HttpStatus.NOT_FOUND;
				    responseMessage = String.format(errorMessage, id);
			 } catch (Exception ex) {
				    responseStatusCode = HttpStatus.EXPECTATION_FAILED;
				    responseMessage = ex.getMessage();
			 }
			 responseDTO = new ResponseDTO(responseMessage, responseStatusCode, responseStatusCode.value());
			 return ResponseEntity.status(responseStatusCode).body(responseDTO);
	   }
	   
	   @PutMapping(value = "/1.0/update")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> updateStudentInfo(@RequestParam(name = "id") String id, @RequestBody StudentDTO studentDTO) {
			 try {
				    objStudentWrapper.updateStudentInfo(id, studentDTO);
				    responseStatusCode = HttpStatus.OK;
				    responseMessage = "Data Updated Successfully";
			 } catch (ResourceNotFoundException ex) {
				    responseMessage = String.format(errorMessage, id);
				    responseStatusCode = HttpStatus.NOT_FOUND;
			 } catch (Exception ex) {
				    responseStatusCode = HttpStatus.NOT_FOUND;
				    responseMessage = ex.getMessage();
			 }
			 responseDTO = new ResponseDTO(responseMessage, responseStatusCode, responseStatusCode.value());
			 return ResponseEntity.status(responseStatusCode).body(responseDTO);
	   }
}
