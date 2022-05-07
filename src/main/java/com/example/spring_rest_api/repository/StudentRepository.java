/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.spring_rest_api.repository;

import com.example.spring_rest_api.model.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gn1398
 */

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    
//    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    public Optional<Student> findStudentByEmail(String email);
    public List<Student> findStudentByName(String name);
    public List<Student> findStudentByDob(String name);
    
}
