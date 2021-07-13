package com.javaadvance.service;

import com.javaadvance.dao.ComputerDao;
import com.javaadvance.entity.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
    @Autowired
    ComputerDao computerDao;

    @Override
    public List<Computer> getAll() {
        return computerDao.findAll();
    }

    @Override
    public Computer getById(int id) {
        return computerDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Computer addComputer(Computer computer) {

        return computerDao.saveAndFlush(computer);
    }

    @Override
    public Computer replaceComputer(int id, Computer computer) {
        computer.setId(id);
        if (!computerDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer with such id isn't found");
        }
        return computerDao.saveAndFlush(computer);
    }

    @Override
    public void deleteComputer(int id) {
        if (!computerDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer with such id isn't found");
        }
        computerDao.deleteById(id);
    }
}
