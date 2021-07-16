package com.javaadvance.service;

import com.javaadvance.dao.ApartmentDao;
import com.javaadvance.dto.ApartmentPage;
import com.javaadvance.entity.Apartment;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentDao apartmentDao;


    @Override
    public ApartmentPage getAllApartments(int page, int size) {
        final Page<Apartment> allInfo = apartmentDao.findAll(PageRequest.of(page, size));
        return new ApartmentPage(allInfo.getContent(),allInfo.getTotalPages(),allInfo.getTotalElements());
    }

    @Override
    public Apartment getApartmentById(int id) {
        return apartmentDao.findById(id).orElseThrow(() -> new ItemNotFoundException("Apartment", id));
    }

    @Override
    public List<Apartment> getApartmentsByAddress(String address) {
        return apartmentDao.findByAddress(address);
    }

    @Override
    public Apartment addApartment(Apartment apartment) {

        return apartmentDao.saveAndFlush(apartment);
    }

    @Override
    public Apartment updateApartment(int id, Apartment apartment) {
        apartment.setId(id);
        if (!apartmentDao.existsById(id)) {
            throw new ItemNotFoundException("Apartment", id);
        }
        return apartmentDao.saveAndFlush(apartment);
    }

    @Override
    public void deleteApartment(int id) {
        if (!apartmentDao.existsById(id)) {
            throw new ItemNotFoundException("Apartment", id);
        }
        apartmentDao.deleteById(id);
    }


}
