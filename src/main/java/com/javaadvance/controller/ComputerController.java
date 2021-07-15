package com.javaadvance.controller;


import com.javaadvance.dto.ComputerPage;
import com.javaadvance.entity.Computer;
import com.javaadvance.service.ComputerService;
import com.javaadvance.validator.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {
    @Autowired
    ComputerService computerService;

    private final static Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ComputerPage getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size){
        LOGGER.info("Handling GET request for all computers");
        return computerService.getAll(page,size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Computer getById(@PathVariable int id){
        LOGGER.info("Handling GET request for computer with id {}",id);
        return computerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Computer addComputer(@RequestBody @Valid Computer computer){
        LOGGER.info("Handling POSt request for computer {}",computer);
        return computerService.addComputer(computer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer replaceComputer(@PathVariable int id, @RequestBody @Valid Computer computer){
        LOGGER.info("Handling PUT request for computer with id {}",id);
        return computerService.replaceComputer(id, computer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComputer(@PathVariable int id){
        LOGGER.info("Handling DELETE request for computer with id {}",id);
        computerService.deleteComputer(id);
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.addValidators(new ComputerValidator());
    }

}
