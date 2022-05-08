
package com.example.spring_rest_api.controller;

import com.example.spring_rest_api.dto.StudentDTO;
import com.example.spring_rest_api.dto.StudentNoDobDTO;
import com.example.spring_rest_api.utility.ResponseBody;
import com.example.spring_rest_api.service.StudentService;
import java.util.List;
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
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("dec", "deleting student");
        ResponseBody<Integer, String> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage("student succesfully deleted");
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "getStudent/id/{id}")
    public ResponseEntity<ResponseBody> getStudent(@PathVariable Long id) {
        StudentNoDobDTO studentNoDobDTO = studentService.getStudent(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "get student by id");
        ResponseBody<Integer, StudentNoDobDTO> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage(studentNoDobDTO);
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "getStudent/email/{email}")
    public ResponseEntity<ResponseBody> getStudentByEmail(@PathVariable String email) {
        StudentNoDobDTO studentNoDobDTO = studentService.getStudentByEmail(email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "get student by email");
        ResponseBody<Integer, StudentNoDobDTO> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage(studentNoDobDTO);
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "getStudent/name/{name}")
    public ResponseEntity<ResponseBody> getStudentByName(@PathVariable String name) {
        List<StudentNoDobDTO> studentNoDobDTOs = studentService.getStudentByName(name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "get student by email");
        ResponseBody<Integer, List<StudentNoDobDTO>> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage(studentNoDobDTOs);
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "getAllStudents")
    public ResponseEntity<ResponseBody> fetchAllStudents() {
        List<StudentNoDobDTO> studentNoDobDTOs = studentService.getAllStudents();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("desc", "get student by email");
        ResponseBody<Integer, List<StudentNoDobDTO>> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage(studentNoDobDTOs);
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @PostMapping(path = "createStudent")
    public ResponseEntity<ResponseBody> createStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("dec", "creating student");
        ResponseBody<Integer, String> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage("student succesfully created");
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @PutMapping(path = "editStudent/{id}")
    public ResponseEntity<ResponseBody> editStudent(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        
        studentService.editStudent(id, name, email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("dec", "editing student");
        ResponseBody<Integer, String> responseBody = new ResponseBody();
        responseBody.setStatus(HttpStatus.OK.value());
        responseBody.setMessage("student succesfully edited");
        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }
}
