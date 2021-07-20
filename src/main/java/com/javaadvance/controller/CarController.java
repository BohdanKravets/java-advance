package com.javaadvance.controller;


import com.javaadvance.dto.CarDto;
import com.javaadvance.entity.Car;
import com.javaadvance.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getCars() {
        return carService.getAll();
    }



    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CarDto replaceCar(@PathVariable int id, @RequestBody CarDto car) {
        return carService.replaceCar(id, car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable int id) {
        carService.removeCar(id);
    }

}
