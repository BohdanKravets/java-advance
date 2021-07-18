package com.javaadvance.service;

import com.javaadvance.dto.ApartmentDto;
import com.javaadvance.dto.ApartmentPage;
import com.javaadvance.entity.Apartment;

import java.util.List;


public interface ApartmentService {

    ApartmentPage getAllApartments(int page, int size);

    ApartmentDto addApartment(ApartmentDto apartment);

    ApartmentDto updateApartment(int id, ApartmentDto apartment);

    void deleteApartment(int id);

    ApartmentDto getApartmentDtoById(int id);

    List<ApartmentDto> getApartmentsByAddress(String address);

    Apartment getApartmentById(int id);
}
