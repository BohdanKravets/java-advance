package com.javaadvance.dao;

import com.javaadvance.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApartmentDao extends JpaRepository<Apartment,Integer> {
    @Query("select ap from Apartment ap where ap.address like :address")
    List<Apartment> findByAddress(String address);
}
