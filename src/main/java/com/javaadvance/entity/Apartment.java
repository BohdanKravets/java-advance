package com.javaadvance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Apartment {
    private int id;
    private String address;
    private int roomsNum;
    public double square;
    public double price;
}
