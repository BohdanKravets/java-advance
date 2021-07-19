package com.javaadvance.service;

import com.javaadvance.dao.CarDao;
import com.javaadvance.dto.CarDto;
import com.javaadvance.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;
    @Autowired
    private UserService userService;

    @Override
    public List<CarDto> getAll() {
        final List<Car> all = carDao.findAll();
        final List<CarDto> carDtos = all.stream()
                .map(car -> {
                    CarDto carDto = new CarDto();
                    carDto.setId(car.getId());
                    carDto.setBrand(car.getBrand());
                    carDto.setColor(car.getColor());
                    carDto.setEngineVolume(car.getEngineVolume());
                    carDto.setUserId(car.getUser().getId());
                    return carDto;
                }).collect(Collectors.toList());
        return carDtos;
    }


    @Override
    public CarDto replaceCar(int id, CarDto car) {
        car.setId(id);
        Car carDB = new Car();
        carDB.setId(car.getId());
        carDB.setBrand(car.getBrand());
        carDB.setColor(car.getColor());
        carDB.setEngineVolume(car.getEngineVolume());
        carDB.setUser(userService.getById(car.getUserId()));
        carDao.saveAndFlush(carDB);
        return car;
    }

    @Override
    public void removeCar(int id) {
carDao.deleteById(id);
    }

    public Car getById(int id){
        return carDao.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }
}
