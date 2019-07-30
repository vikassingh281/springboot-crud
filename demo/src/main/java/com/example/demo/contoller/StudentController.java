
package com.example.demo.contoller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.impl.IStudentDAO;
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
	   
	   @Autowired
	   private IStudentDAO studentDAO;
	   
	   @PostMapping(value = "/1.0/save")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> save(@RequestBody StudentDTO studentDTO) {
			 ResponseDTO responseDTO;
			 HttpStatus status;
			 try {
				    studentDAO.saveStudentInfo(studentDTO);
				    responseDTO = new ResponseDTO("Data Saved Successfully", HttpStatus.OK, HttpStatus.OK.value());
				    status = HttpStatus.OK;
			 } catch (Exception ex) {
				    responseDTO = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
				    status = HttpStatus.NOT_FOUND;
			 }
			 return ResponseEntity.status(status).body(responseDTO);
	   }
	   
	   @GetMapping(value = "/1.0/getstudentinfo")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> getStudentInfo(@RequestParam(name = "id") String id) {
			 ResponseDTO responseDTO;
			 HttpStatus status;
			 try {
				    StudentEntity studentEntity = studentDAO.getStudentInfo(id);
				    status = HttpStatus.OK;
				    responseDTO = new ResponseDTO("Data Found successfully", status, status.value(), studentEntity);
			 } catch (ResourceNotFoundException ex) {
				    String message = String.format(errorMessage, id);
				    status = HttpStatus.NOT_FOUND;
				    responseDTO = new ResponseDTO(message, status, status.value(), null);
			 } catch (Exception ex) {
				    status = HttpStatus.PRECONDITION_FAILED;
				    responseDTO = new ResponseDTO(ex.getMessage(), status, status.value(), null);
			 }
			 return ResponseEntity.status(status).body(responseDTO);
	   }
	   
	   @DeleteMapping(value = "/1.0/delete")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> deleteStudentInfo(@RequestParam(name = "id") String id) {
			 ResponseDTO responseDTO;
			 HttpStatus status;
			 try {
				    studentDAO.deleteStudentInfo(id);
				    status = HttpStatus.OK;
				    responseDTO = new ResponseDTO("Data Delete Successfully", status, status.value());
			 } catch (EmptyResultDataAccessException ex) {
				    status = HttpStatus.NOT_FOUND;
				    String message = String.format(errorMessage, id);
				    responseDTO = new ResponseDTO(message, status, status.value());
			 } catch (Exception ex) {
				    status = HttpStatus.EXPECTATION_FAILED;
				    responseDTO = new ResponseDTO(ex.getMessage(), status, status.value());
			 }
			 return ResponseEntity.status(status).body(responseDTO);
	   }
	   
	   @PutMapping(value = "/1.0/update")
	   @ResponseBody
	   public ResponseEntity<ResponseDTO> updateStudentInfo(@RequestParam(name = "id") String id, @RequestBody StudentDTO studentDTO) {
			 ResponseDTO responseDTO;
			 HttpStatus status;
			 try {
				    studentDAO.updateStudentInfo(id, studentDTO);
				    status = HttpStatus.OK;
				    responseDTO = new ResponseDTO("Data Updated Successfully", status, status.value());
			 } catch (ResourceNotFoundException ex) {
				    String message = String.format(errorMessage, id);
				    status = HttpStatus.NOT_FOUND;
				    responseDTO = new ResponseDTO(message, status, status.value(), null);
			 } catch (Exception ex) {
				    status = HttpStatus.NOT_FOUND;
				    responseDTO = new ResponseDTO(ex.getMessage(), status, status.value());
			 }
			 return ResponseEntity.status(status).body(responseDTO);
	   }
}
