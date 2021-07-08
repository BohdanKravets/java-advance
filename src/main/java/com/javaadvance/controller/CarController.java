package com.javaadvance.controller;


import com.javaadvance.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    ArrayList<Car> cars = new ArrayList<>();

    {
        cars.add(new Car(1, "Audi", 2.5, "red"));
        cars.add(new Car(2, "BMW", 2.0, "green"));
        cars.add(new Car(3, "VW", 2.4, "black"));
        cars.add(new Car(4, "Dodge", 3.6, "blue"));
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Car> getCars() {
        return cars;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car addCar(@RequestBody Car car) {
        cars.add(car);
        return car;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Car replaceCar(@PathVariable int id, @RequestBody Car car) {

        final Optional<Car> chosenCar = cars
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        final Car carToReplace = chosenCar.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final int index = cars.indexOf(carToReplace);

        cars.set(index,car);
        return car;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable int id) {
        cars.removeIf(car -> car.getId() == id);
    }

}
