package com.javaadvance.service;

import com.javaadvance.dao.UserDao;
import com.javaadvance.dto.UserDto;
import com.javaadvance.entity.Apartment;
import com.javaadvance.entity.User;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ApartmentService apartmentService;

    @Override
    public UserDto addUser(UserDto user) {
        User userDB = new User();
        userDB.setId(user.getId());
        userDB.setEmail(user.getEmail());
        userDB.setBirthDate(user.getBirthDate());
        userDB.setName(user.getName());
        final List<Apartment> apartmentsDB = user.getApartmentIds().stream()
                .map(id -> apartmentService.getApartmentById(id))
                .collect(Collectors.toList());
        userDB.setApartmentList(apartmentsDB);
        userDao.saveAndFlush(userDB);
        user.setId(userDB.getId());
        return user;
    }

    @Override
    public UserDto updateUser(int id, UserDto user) {
        user.setId(id);
        User userDB = new User();
        userDB.setId(user.getId());
        userDB.setEmail(user.getEmail());
        userDB.setBirthDate(user.getBirthDate());
        userDB.setName(user.getName());
        final List<Apartment> apartmentsDB = user.getApartmentIds().stream()
                .map(idn -> apartmentService.getApartmentById(idn))
                .collect(Collectors.toList());
        userDB.setApartmentList(apartmentsDB);
        userDao.saveAndFlush(userDB);
        return user;
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDto getUserDtoById(int id) {
        final Optional<User> chosenUser = userDao.findById(id);
        Optional<UserDto> chosenUserDto = Optional.of(new UserDto());
        chosenUserDto.get().setId(chosenUser.get().getId());
        chosenUserDto.get().setEmail(chosenUser.get().getEmail());
        chosenUserDto.get().setBirthDate(chosenUser.get().getBirthDate());
        chosenUserDto.get().setName(chosenUser.get().getName());
        chosenUserDto.get().setApartmentIds(chosenUser.get()
                .getApartmentList()
                .stream().map(Apartment::getId)
                .collect(Collectors.toList()));

        return chosenUserDto.orElseThrow(() -> new ItemNotFoundException("User", id));
    }

    public User getUserById(int id) {


        return userDao.findById(id).orElseThrow(() -> new ItemNotFoundException("User", id));
    }
}
