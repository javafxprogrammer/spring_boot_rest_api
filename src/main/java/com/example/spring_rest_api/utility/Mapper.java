/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.utility;

import com.example.spring_rest_api.dto.StudentDTO;
import com.example.spring_rest_api.dto.StudentNoDobDTO;
import com.example.spring_rest_api.model.Student;
import org.springframework.stereotype.Component;

/**
 *
 * @author gn1398
 */
@Component
public class Mapper {

    public Student DTOtoStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setDob(studentDTO.getDob());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }

    public StudentNoDobDTO studentToDTO(Student student) {
        StudentNoDobDTO studentDTO = new StudentNoDobDTO();
        studentDTO.setId(student.getId());
        studentDTO.setAge(student.getAge());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }
    
}
