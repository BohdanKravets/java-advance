package com.javaadvance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    int id;
    String name;
    String email;
    LocalDate birthDate;

    List<Integer> apartmentIds;
}
