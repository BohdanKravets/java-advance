package com.javaadvance.service;

import com.javaadvance.dao.ApartmentDao;
import com.javaadvance.dto.ApartmentDto;
import com.javaadvance.dto.ApartmentPage;
import com.javaadvance.entity.Apartment;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private UserService userService;


    @Override
    public ApartmentPage getAllApartments(int page, int size) {
        final Page<Apartment> allInfo = apartmentDao.findAll(PageRequest.of(page, size));
        final List<Apartment> apartmentList = allInfo.getContent();
        final List<ApartmentDto> apartmentDtos = apartmentList.stream()
                .map(this::getApartmentDto).collect(Collectors.toList());

        return new ApartmentPage(apartmentDtos, allInfo.getTotalPages(), allInfo.getTotalElements());
    }

    @Override
    public ApartmentDto getApartmentDtoById(int id) {
        final Apartment apartment = apartmentDao.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("apartment", id));
        return getApartmentDto(apartment);
    }

    private ApartmentDto getApartmentDto(Apartment apartment) {
        ApartmentDto apartmentDto = new ApartmentDto();
        apartmentDto.setId(apartment.getId());
        apartmentDto.setAddress(apartment.getAddress());
        apartmentDto.setPrice(apartment.getPrice());
        apartmentDto.setSquare(apartment.getSquare());
        apartmentDto.setRoomsNum(apartment.getRoomsNum());
        apartmentDto.setUserId(apartment.getUser().getId());
        return apartmentDto;
    }

    @Override
    public List<ApartmentDto> getApartmentsByAddress(String address) {
        final List<Apartment> byAddress = apartmentDao.findByAddress(address);

        return byAddress.stream().map(this::getApartmentDto).collect(Collectors.toList());
    }

    @Override
    public ApartmentDto addApartment(ApartmentDto apartment) {

        final Apartment apartmentDB = getApartment(apartment);

        apartmentDao.saveAndFlush(apartmentDB);
        apartment.setId(apartmentDB.getId());
        return apartment;
    }

    @Override
    public ApartmentDto updateApartment(int id, ApartmentDto apartment) {
        apartment.setId(id);
        if (!apartmentDao.existsById(id)) {
            throw new ItemNotFoundException("Apartment", id);
        }
        final Apartment apartmentDB = getApartment(apartment);
        apartmentDao.saveAndFlush(apartmentDB);
        return apartment;
    }

    private Apartment getApartment(ApartmentDto apartment) {
        Apartment apartmentDB = new Apartment();
        apartmentDB.setId(apartment.getId());
        apartmentDB.setAddress(apartment.getAddress());
        apartmentDB.setPrice(apartment.getPrice());
        apartmentDB.setSquare(apartment.getSquare());
        apartmentDB.setPrice(apartment.getPrice());
        apartmentDB.setRoomsNum(apartment.getRoomsNum());
        apartmentDB.setUser(userService.getUserById(apartment.getUserId()));
        return apartmentDB;
    }

    @Override
    public void deleteApartment(int id) {
        if (!apartmentDao.existsById(id)) {
            throw new ItemNotFoundException("Apartment", id);
        }
        apartmentDao.deleteById(id);
    }


    public Apartment getApartmentById(int id) {

        return apartmentDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Apartment", id));

    }

}
