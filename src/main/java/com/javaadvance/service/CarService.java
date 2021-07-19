package com.javaadvance.service;


import com.javaadvance.dto.CarDto;
import com.javaadvance.entity.Car;

import java.util.List;

public interface CarService {


    List<CarDto> getAll();


    CarDto replaceCar(int id, CarDto car);

    void removeCar(int id);

    Car getById(int id);
}
