/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.service;

import com.example.spring_rest_api.dto.StudentDTO;
import com.example.spring_rest_api.model.Student;
import com.example.spring_rest_api.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gn1398
 */
@Service // tell spring that this is a spring bean
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }
    
    public List<StudentDTO> getAllStudents() {
         return studentRepository.findAll().stream().map(this::studentToDTO).collect(Collectors.toList());
    }
    
    private StudentDTO studentToDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setAge(student.getAge());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public void createStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("failed to create new student because email already exist");
        } else {
            studentRepository.save(student);
        }
    }

    public void editStudent(Long id, String name, String email) {
        Optional<Student> studenOptional = studentRepository.findById(id);

        if (!studenOptional.isPresent()) {
            throw new IllegalStateException("failed to update student with id " + id + " because they do not exist");
        } else {

            if (email != null && email.length() > 0) {
                if (studentRepository.findStudentByEmail(email).isPresent()) {
                    throw new IllegalStateException("failed to update email because email already exist");
                } else {
                    studenOptional.get().setEmail(email);
                }
            }

            if (name != null && name.length() > 0) {
                if (studenOptional.get().getName().equals(name)) {
                    throw new IllegalStateException("failed to update name because name has not changed");
                } else {
                    studenOptional.get().setName(name);
                }
            }
             studentRepository.save(studenOptional.get());
        }
    }

    public void deleteStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent()) {
            throw new IllegalStateException("failed to delete student because student with id " + id + " does not exist");
        } else {
            studentRepository.delete(studentRepository.findById(id).get());
        }
    }

    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }
}
