/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.controllers;

import com.example.spring_rest_api.models.Student;
import com.example.spring_rest_api.services.StudentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gn1398 API layer
 */
@RestController // declared this class as a rest controller
@RequestMapping(path = "api/v1/student") // set the url for student
public class StudentController {

    private final StudentService studentService;

    @Autowired // automatical instantiate studentService for us / dependency injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping(path = "deleteStudent/id/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping(path = "getStudent/id/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping(path = "getStudent/email/{email}")
    public Optional<Student> getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping(path = "getStudent/name/{name}")
    public List<Student> getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping(path = "getAllStudents")
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(path = "createStudent")
    public void createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @PutMapping(path = "editStudent/{id}")
    public void editStudent(
            @PathVariable Long id, 
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.editStudent(id, name, email);
    }
}
