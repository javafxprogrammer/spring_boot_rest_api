/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.services;

import com.example.spring_rest_api.model.Student;
import com.example.spring_rest_api.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
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

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public void editStudent(Student student) {
      Optional<Student> student1 = studentRepository.findById(student.getId());
      student1.get().setName(student.getName());
      student1.get().setEmail(student.getEmail());
      student1.get().setDob(student.getDob());
      student1.get().setAge(student.getAge());
      studentRepository.save(student1.get());
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(studentRepository.findById(id).get());
    }
}

