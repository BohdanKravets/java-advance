package com.javaadvance.controller;

import com.javaadvance.entity.Apartment;
import com.javaadvance.service.ApartmentService;
import com.javaadvance.validator.ApartmentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private ApartmentValidator apartmentValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApartmentController.class);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartmentList() {
        return apartmentService.getAllApartments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Apartment getApartmentById(@PathVariable int id) {
        return apartmentService.getApartmentById(id);
    }

    @GetMapping("/address/{address}")
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartmentsByAddress(@PathVariable String address) {
        LOGGER.info("Handling GET request with address {}", address);
        return apartmentService.getApartmentsByAddress(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Apartment addApartment(@RequestBody @Valid Apartment apartment) {
        return apartmentService.addApartment(apartment);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Apartment replaceApartment(@PathVariable int id, @RequestBody @Valid Apartment apartment) {

        return apartmentService.updateApartment(id, apartment);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApartment(@PathVariable int id) {
        apartmentService.deleteApartment(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(apartmentValidator);

    }

}
