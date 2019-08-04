package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "demo1")
/*@Table(name = "studentdetail")*/
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity {
	   
	   @Id
	   @Column(name = "id", nullable = false, length = 36)
	   private String uniqueId;
	   
	   @Column(name = "firstname", nullable = false, length = 100)
	   private String firstName;
	   
	   @Column(name = "lastname", nullable = true, length = 100)
	   private String lastName;
	   
	   @Column(name = "department", nullable = false, length = 100)
	   private String department;
	   
	   @Column(name = "createddate", nullable = false, length = 100)
	   private String createdDate;
	   
	   @Column(name = "updateddate", nullable = false, length = 100)
	   private String updatedDate;
	   
}
