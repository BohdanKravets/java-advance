package com.javaadvance.dao;

import com.javaadvance.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerDao extends JpaRepository<Computer,Integer> {
}
