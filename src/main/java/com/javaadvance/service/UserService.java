package com.javaadvance.service;

import com.javaadvance.dto.UserDto;
import com.javaadvance.entity.Car;
import com.javaadvance.entity.User;

import java.util.List;

public interface UserService {
   List<UserDto> getAll();


    UserDto addUser(UserDto user);

    UserDto replaceUser(int id, UserDto user);

    void removeUser(int id);

    User getById(int id);

    void addCarByUserId(int id, Car car);
}
