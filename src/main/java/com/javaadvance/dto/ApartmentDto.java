package com.javaadvance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class ApartmentDto {
    private int id;
    private int roomsNum;
    public double square;
    public double price;
    private String address;

    private int userId;
}
