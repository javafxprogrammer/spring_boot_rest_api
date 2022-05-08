/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.spring_rest_api.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gn1398
 * @param <T>
 * @param <U>
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponseBody<T, U> {
    T status;
    U message;
}
