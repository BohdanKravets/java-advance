package com.javaadvance.dao;

import com.javaadvance.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentDao extends JpaRepository<Apartment,Integer> {
}
