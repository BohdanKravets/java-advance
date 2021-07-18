package com.javaadvance.controller;

import com.javaadvance.dto.ApartmentDto;
import com.javaadvance.dto.ApartmentPage;
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
    public ApartmentPage getApartmentList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
        LOGGER.info("Handling GET request with for all apartments");

        return apartmentService.getAllApartments(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApartmentDto getApartmentById(@PathVariable int id) {
        LOGGER.info("Handling GET request with apartment id {}", id);
        return apartmentService.getApartmentDtoById(id);
    }

    @GetMapping("/address/{address}")
    @ResponseStatus(HttpStatus.OK)
    public List<ApartmentDto> getApartmentsByAddress(@PathVariable String address) {
        LOGGER.info("Handling GET request with apartment address {}", address);
        return apartmentService.getApartmentsByAddress(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApartmentDto addApartment(@RequestBody @Valid ApartmentDto apartment) {
        LOGGER.info("Handling POST request with apartment {}", apartment);

        return apartmentService.addApartment(apartment);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApartmentDto replaceApartment(@PathVariable int id, @RequestBody @Valid ApartmentDto apartment) {
        LOGGER.info("Handling POST request with apartment id {} and apartment", apartment);

        return apartmentService.updateApartment(id, apartment);

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteApartment(@PathVariable int id) {
        LOGGER.info("Handling DELETE request with apartment id {}", id);
        apartmentService.deleteApartment(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(apartmentValidator);

    }

}
