package com.javaadvance.controller;

import com.javaadvance.entity.Apartment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    public List<Apartment> apartmentList = new ArrayList<>();

    {
        apartmentList.add(new Apartment(1,"Zelena, 201",2,65.4,5000));
        apartmentList.add(new Apartment(2,"Franka, 10",2,70.1,6000));
        apartmentList.add(new Apartment(3,"Horodotska, 225",1,45.2,4500));
        apartmentList.add(new Apartment(4,"Lychakivska, 11",3,90.3,7000));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Apartment> getApartmentList(){
        return apartmentList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Apartment addApartment(@RequestBody Apartment apartment){
        this.apartmentList.add(apartment);
        return apartment;
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Apartment replaceApartment(@PathVariable int id, @RequestBody Apartment apartment) {
        final Optional<Apartment> chosenApartment = apartmentList.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        final Apartment apartmentToReplace = chosenApartment.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final int apartmentIndex = apartmentList.indexOf(apartmentToReplace);
        apartmentList.set(apartmentIndex, apartment);

        return apartment;

    }

    @DeleteMapping( "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteApartment(@PathVariable int id) {
        apartmentList.removeIf(apartment -> apartment.getId() ==id);
   }






}
