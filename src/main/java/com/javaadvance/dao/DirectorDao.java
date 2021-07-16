package com.javaadvance.dao;

import com.javaadvance.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorDao extends JpaRepository<Director,Integer> {

}
