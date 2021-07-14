package com.javaadvance.service;

import com.javaadvance.entity.Apartment;

import java.util.List;


public interface ApartmentService {

    List<Apartment> getAllApartments();

    Apartment addApartment(Apartment apartment);

    Apartment updateApartment(int id, Apartment apartment);

    void deleteApartment(int id);

    Apartment getApartmentById(int id);

    List<Apartment> getApartmentsByAddress(String address);
}
