package com.javaadvance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Car {
    private int id;
    private String brand;
    private double engineVolume;
    private String color;
}
