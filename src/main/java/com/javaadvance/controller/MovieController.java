package com.javaadvance.controller;

import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;
import com.javaadvance.service.MovieService;
import com.validator.MovieValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    private final static Logger LOG = LoggerFactory.getLogger(MovieController.class);




    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MoviePage getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return movieService.getAllMovies(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieById(@PathVariable int id ) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody @Valid Movie movie) {
        LOG.info("Handling Post request for object {}",movie);
        return movieService.createMovie(movie);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(new MovieValidator());
    }
}
