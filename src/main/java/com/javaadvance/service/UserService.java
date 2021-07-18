package com.javaadvance.service;

import com.javaadvance.dto.UserDto;
import com.javaadvance.entity.User;


public interface UserService {

    UserDto addUser(UserDto user);

    UserDto updateUser(int id, UserDto user);

    void deleteUser(int id);

    UserDto getUserDtoById(int id);
    User getUserById(int id);

}
