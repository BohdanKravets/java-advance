package com.javaadvance.service;

import com.javaadvance.dto.ApartmentPage;
import com.javaadvance.entity.Apartment;

import java.util.List;


public interface ApartmentService {

    ApartmentPage getAllApartments(int page, int size);

    Apartment addApartment(Apartment apartment);

    Apartment updateApartment(int id, Apartment apartment);

    void deleteApartment(int id);

    Apartment getApartmentById(int id);

    List<Apartment> getApartmentsByAddress(String address);
}
