package com.javaadvance.service;

import com.javaadvance.dao.ApartmentDao;
import com.javaadvance.entity.Apartment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class ApartmentServiceImpl implements ApartmentService {
   @Autowired
   private ApartmentDao apartmentDao;


    @Override
    public List<Apartment> getAllApartments() {
        return apartmentDao.findAll();
    }

    @Override
    public Apartment getApartmentById(int id) {
        return apartmentDao.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Apartment> getApartmentsByAddress(String address) {
        return apartmentDao.findByAddress(address);
    }

    @Override
    public Apartment addApartment(Apartment apartment) {
        if(!CharUtils.isAsciiAlphaUpper(apartment.getAddress().charAt(0))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"should start with capital letter");
        }
        return apartmentDao.saveAndFlush(apartment);
    }

    @Override
    public Apartment updateApartment(int id, Apartment apartment) {
        apartment.setId(id);
        if(!apartmentDao.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return apartmentDao.saveAndFlush(apartment);
    }

    @Override
    public void deleteApartment(int id) {
        if(!apartmentDao.existsById(id))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       apartmentDao.deleteById(id);
    }


}
