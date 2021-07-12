package com.javaadvance.controller;

import com.javaadvance.entity.Movie;
import com.javaadvance.service.MovieService;
import com.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;




    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(value = "/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody @Valid Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping(value = "/movie/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "movie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new MovieValidator());
    }
}
