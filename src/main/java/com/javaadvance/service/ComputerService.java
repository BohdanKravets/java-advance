package com.javaadvance.service;

import com.javaadvance.entity.Computer;

import java.util.ArrayList;
import java.util.List;

public interface ComputerService {
    List<Computer> getAll();

    Computer getById(int id);

    Computer addComputer(Computer computer);

    Computer replaceComputer(int id, Computer computer);

    void deleteComputer(int id);
}
