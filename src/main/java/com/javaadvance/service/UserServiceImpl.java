package com.javaadvance.service;

import com.javaadvance.dao.CarDao;
import com.javaadvance.dao.UserDao;
import com.javaadvance.dto.UserDto;
import com.javaadvance.entity.Car;
import com.javaadvance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CarService carService;
    @Autowired
    private CarDao carDao;


    @Override
    public List<UserDto> getAll() {
        final List<User> all = userDao.findAll();
        final List<UserDto> userDtos = all.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getName());
                    userDto.setAge(user.getAge());

                    final List<Integer> carIds = user.getCars().stream()
                            .map(Car::getId)
                            .collect(Collectors.toList());
                    userDto.setCarsIds(carIds);
                    return userDto;
                })
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto addUser(UserDto user) {
        User userDB = new User();
        userDB.setName(user.getName());
        userDB.setAge(user.getAge());
        userDB.setCars(new ArrayList<>());
        userDao.saveAndFlush(userDB);
        user.setId(userDB.getId());
        return user;
    }

    @Override
    public UserDto replaceUser(int id, UserDto user) {
        user.setId(id);
        User userDB = new User();
        userDB.setId(id);
        userDB.setName(user.getName());
        userDB.setAge(user.getAge());
        if (user.getCarsIds() == null) {
            user.setCarsIds(new ArrayList<>());
        }
        final List<Car> cars = user.getCarsIds().stream()
                .map(idn -> carService.getById(id))
                .collect(Collectors.toList());

        userDB.setCars(cars);
        userDao.saveAndFlush(userDB);
        return user;
    }

    @Override
    public void removeUser(int id) {
        userDao.deleteById(id);
    }

    public User getById(int id) {
        return userDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @Override
    public User addCarByUserId(int id, Car car) {
        User user = userDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        carDao.saveAndFlush(car);
        car.setUser(user);
        userDao.saveAndFlush(user);
//        car.setUser(user);
//        user.getCars().add(car);
        return user;
    }
}
