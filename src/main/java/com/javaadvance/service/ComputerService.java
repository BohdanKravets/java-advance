package com.javaadvance.service;

import com.javaadvance.dto.ComputerPage;
import com.javaadvance.entity.Computer;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface ComputerService {
    ComputerPage getAll(int page, int size);

    Computer getById(int id);

    Computer addComputer(Computer computer);

    Computer replaceComputer(int id, Computer computer);

    void deleteComputer(int id);
}
