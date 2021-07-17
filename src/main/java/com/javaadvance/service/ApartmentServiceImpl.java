package com.javaadvance.service;

import com.javaadvance.dao.ApartmentDao;
import com.javaadvance.dao.UserDao;
import com.javaadvance.dto.ApartmentDto;
import com.javaadvance.dto.ApartmentPage;
import com.javaadvance.entity.Apartment;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private UserService userDao;


    @Override
    public ApartmentPage getAllApartments(int page, int size) {
        final Page<Apartment> allInfo = apartmentDao.findAll(PageRequest.of(page, size));
        final List<Apartment> apartmentList = allInfo.getContent();
        final List<ApartmentDto> apartmentDtos = apartmentList.stream().map(apartment -> {
            ApartmentDto apartmentDto = new ApartmentDto();
            apartmentDto.setId(apartment.getId());
            apartmentDto.setAddress(apartment.getAddress());
            apartmentDto.setPrice(apartment.getPrice());
            apartmentDto.setSquare(apartment.getSquare());
            apartmentDto.setPrice(apartment.getPrice());
            apartmentDto.setRoomsNum(apartment.getRoomsNum());
            apartmentDto.setUserId(apartment.getUser().getId());
            return apartmentDto;
        }).collect(Collectors.toList());

        return new ApartmentPage(apartmentDtos, allInfo.getTotalPages(), allInfo.getTotalElements());
    }

    @Override
    public ApartmentDto getApartmentById(int id) {
        final Optional<Apartment> chosenApartment = apartmentDao.findById(id);
        Optional<ApartmentDto> chosenApartmentDto = Optional.of(new ApartmentDto());
        chosenApartmentDto.get().setId(chosenApartment.get().getId());
        chosenApartmentDto.get().setAddress(chosenApartment.get().getAddress());
        chosenApartmentDto.get().setPrice(chosenApartment.get().getPrice());
        chosenApartmentDto.get().setSquare(chosenApartment.get().getSquare());
        chosenApartmentDto.get().setPrice(chosenApartment.get().getPrice());
        chosenApartmentDto.get().setRoomsNum(chosenApartment.get().getRoomsNum());
        chosenApartmentDto.get().setUserId(chosenApartment.get().getUser().getId());

        return chosenApartmentDto.orElseThrow(() -> new ItemNotFoundException("Apartment", id));
    }

    @Override
    public List<ApartmentDto> getApartmentsByAddress(String address) {
        final List<Apartment> byAddress = apartmentDao.findByAddress(address);
        final List<ApartmentDto> apartmentDtos = byAddress.stream().map(apartment -> {
            ApartmentDto apartmentDto = new ApartmentDto();
            apartmentDto.setId(apartment.getId());
            apartmentDto.setAddress(apartment.getAddress());
            apartmentDto.setPrice(apartment.getPrice());
            apartmentDto.setSquare(apartment.getSquare());
            apartmentDto.setPrice(apartment.getPrice());
            apartmentDto.setRoomsNum(apartment.getRoomsNum());
            apartmentDto.setUserId(apartment.getUser().getId());
            return apartmentDto;
        }).collect(Collectors.toList());

        return apartmentDtos;
    }

    @Override
    public ApartmentDto addApartment(ApartmentDto apartment) {

//        Apartment apartmentDB = new Apartment();
//        apartmentDB.setId(apartment.getId());
//        apartmentDB.setAddress(apartment.getAddress());
//        apartmentDB.setPrice(apartment.getPrice());
//        apartmentDB.setSquare(apartment.getSquare());
//        apartmentDB.setPrice(apartment.getPrice());
//        apartmentDB.setRoomsNum(apartment.getRoomsNum());
//        apartmentDB.setUser(userDao.getUserById(apartment.getUserId()));
//        apartmentDao.saveAndFlush(apartmentDB);
//        apartment.setId(apartmentDB.getId());
        return apartment;
    }

//    @Override
//    public ApartmentDto updateApartment(int id, ApartmentDto apartment) {
//        apartment.setId(id);
//        if (!apartmentDao.existsById(id)) {
//            throw new ItemNotFoundException("Apartment", id);
//        }
//        Apartment apartmentDB = new Apartment();
//        apartmentDB.setId(apartment.getId());
//        apartmentDB.setAddress(apartment.getAddress());
//        apartmentDB.setPrice(apartment.getPrice());
//        apartmentDB.setSquare(apartment.getSquare());
//        apartmentDB.setPrice(apartment.getPrice());
//        apartmentDB.setRoomsNum(apartment.getRoomsNum());
//        apartmentDB.setUser(userService.getUserById(apartment.getUserId()));
//        apartmentDao.saveAndFlush(apartmentDB);
//        return apartment;
//    }

    @Override
    public void deleteApartment(int id) {
        if (!apartmentDao.existsById(id)) {
            throw new ItemNotFoundException("Apartment", id);
        }
        apartmentDao.deleteById(id);
    }


}
