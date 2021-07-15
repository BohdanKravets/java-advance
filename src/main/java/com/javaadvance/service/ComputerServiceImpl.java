package com.javaadvance.service;

import com.javaadvance.dto.ComputerPage;
import com.javaadvance.exceptions.ItemNotFoundException;
import com.javaadvance.dao.ComputerDao;
import com.javaadvance.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    ComputerDao computerDao;

    @Override
    public ComputerPage getAll(int page, int size) {
        final Page<Computer> all = computerDao.findAll(PageRequest.of(page, size));

        return new ComputerPage(all.getContent(),all.getTotalPages(),all.getTotalElements());
    }

    @Override
    public Computer getById(int id) {
        return computerDao.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public Computer addComputer(Computer computer) {

        return computerDao.saveAndFlush(computer);
    }

    @Override
    public Computer replaceComputer(int id, Computer computer) {
        computer.setId(id);
        if (!computerDao.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        return computerDao.saveAndFlush(computer);
    }

    @Override
    public void deleteComputer(int id) {
        if (!computerDao.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        computerDao.deleteById(id);
    }
}
