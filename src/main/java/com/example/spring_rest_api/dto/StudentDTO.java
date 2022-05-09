/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gn1398
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

}
