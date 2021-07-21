package com.javaadvance.controller;

import com.javaadvance.dto.MovieDto;
import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;
import com.javaadvance.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@Controller
//@Component
//@Repository
//@Service
//@Bean
//@ControllerAdvice
//@RestControllerAdvice
@RequestMapping(value = "/movie")
public class MovieController {

    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;



    //  @RequestMapping(value = "/movie", method = RequestMethod.GET)
    @GetMapping
    public MoviePage getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return movieService.getAllMovies(page, size);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/title/{title}")
    public Movie getMovieByTitle(@PathVariable String title) {
        return movieService.getMovieByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto insertMovie(@RequestBody @Valid MovieDto movie) {
        LOG.info("Handling POST request for object {}", movie);
        return movieService.createMovie(movie);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie updateMovie(@PathVariable int id, @RequestBody @Valid Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }
}