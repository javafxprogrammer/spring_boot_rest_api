/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.Period;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AccessLevel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import javax.validation.constraints.NotBlank;


//import org.springframework.data.annotation.Id;

/**
 *
 * @author gn1398
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student {
    
    @Id
    @SequenceGenerator(name="student_sequence", sequenceName="student_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_sequence")
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(length=128)
    private String name;
    @Column(length=256, unique = true)
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;
    
    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }
   
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }  
}
