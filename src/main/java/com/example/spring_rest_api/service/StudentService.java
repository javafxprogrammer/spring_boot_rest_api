/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.service;

import com.example.spring_rest_api.dto.StudentNoDobDTO;
import com.example.spring_rest_api.dto.StudentDTO;
import com.example.spring_rest_api.model.Student;
import com.example.spring_rest_api.repository.StudentRepository;
import com.example.spring_rest_api.utility.Mapper;

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
    
    @Autowired
    private Mapper mapper;

    public StudentNoDobDTO getStudent(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            return mapper.studentToDTO(studentOptional.get());
        } else {
            throw new IllegalStateException("student of id " + id + " does not exist");
        }
    }

    public List<StudentNoDobDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(mapper::studentToDTO).collect(Collectors.toList());
    }

    public void createStudent(StudentDTO studentDTO) {
        Student student = mapper.DTOtoStudent(studentDTO);
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("failed to create new student because email " + student.getEmail() + " already exist");
        }
        studentRepository.save(student);
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
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentRepository.findById(id).get());
        } else {
            throw new IllegalStateException("failed to delete student because student with id " + id + " does not exist");
        }
    }

    public StudentNoDobDTO getStudentByEmail(String email) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if (studentOptional.isPresent()) {
            return mapper.studentToDTO(studentOptional.get());
        }
        return null;
    }

    public List<StudentNoDobDTO> getStudentByName(String name) {
        List<Student> students = studentRepository.findStudentByName(name);
        return students.stream().map(mapper::studentToDTO).collect(Collectors.toList());
    }

}
