/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.controller;

import com.example.spring_rest_api.dto.StudentDTO;
import com.example.spring_rest_api.dto.StudentNoDobDTO;
import com.example.spring_rest_api.model.Student;
import com.example.spring_rest_api.utility.ResponseBody;
import com.example.spring_rest_api.service.StudentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseBody> getStudent(@PathVariable Long id) {
        StudentNoDobDTO studentNoDobDTO = studentService.getStudent(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "get student by id");
        ResponseBody<StudentNoDobDTO> responseBody = new ResponseBody();
        responseBody.setStatus(200);
        responseBody.setMessage(studentNoDobDTO);
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
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
    public List<StudentNoDobDTO> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(path = "createStudent")
    public ResponseEntity<ResponseBody> createStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("dec", "creating student");
        ResponseBody<String> responseBody = new ResponseBody();
        responseBody.setStatus(200);
        responseBody.setMessage("student succesfully created");
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @PutMapping(path = "editStudent/{id}")
    public void editStudent(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.editStudent(id, name, email);
    }
}
