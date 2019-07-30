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
    @Column(name = "id")
    private String uniqueId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "department")
    private String department;

    @Column(name = "createddate")
    private String createdDate;

    @Column(name = "updateddate")
    private String updatedDate;

}
