package com.javaadvance.controller;

import com.javaadvance.dto.UserDto;
import com.javaadvance.entity.Car;
import com.javaadvance.entity.User;
import com.javaadvance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getAll();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto replaceUser(@PathVariable int id, @RequestBody UserDto user) {
        return userService.replaceUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        userService.removeUser(id);
    }

    @PostMapping("/{id}/car")
    @ResponseStatus(HttpStatus.CREATED)
    public User addCarByUserId(@PathVariable int id, @RequestBody Car car){
      return  userService.addCarByUserId(id,car);
    }
}
