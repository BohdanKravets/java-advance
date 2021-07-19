package com.javaadvance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class UserDto {

    private int id;
    private String name;
    private int age;
    private List<Integer> carsIds;
}