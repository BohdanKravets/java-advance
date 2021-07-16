package com.javaadvance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MovieDto {
    private int id;
    private String title;
    private int duration;
    private int directorId;
}
