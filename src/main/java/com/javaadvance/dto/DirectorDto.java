package com.javaadvance.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class DirectorDto {
    private int id;
    private String name;
    private List<Integer> movies;
}
