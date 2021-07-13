package com.javaadvance.controller;


import com.javaadvance.entity.Computer;
import com.javaadvance.service.ComputerService;
import com.javaadvance.validator.ComputerValidator;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Computer> getAll(){
        return computerService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Computer getById(@PathVariable int id){
        return computerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Computer addComputer(@RequestBody @Valid Computer computer){
        return computerService.addComputer(computer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Computer replaceComputer(@PathVariable int id, @RequestBody @Valid Computer computer){
        return computerService.replaceComputer(id, computer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComputer(@PathVariable int id){
        computerService.deleteComputer(id);
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        dataBinder.addValidators(new ComputerValidator());
    }

}
