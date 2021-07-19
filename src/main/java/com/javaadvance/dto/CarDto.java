package com.javaadvance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarDto {

    private int id;
    private String brand;
    private double engineVolume;
    private String color;

    private  int userId;
}
