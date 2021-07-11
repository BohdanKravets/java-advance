package com.javaadvance.controller;

import com.javaadvance.entity.Movie;
import com.javaadvance.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieController {
    @Autowired
    private MovieService movieService;

    //    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping(value = "/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping(value = "/movie/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
//        final Optional<Movie> first = movies
//                .stream()
//                .filter(m -> m.getId() == id)
//                .findFirst();
//        final Movie movieInList = first
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No movie found"));
//        final int indexOfMovie = movies.indexOf(movieInList);
//        movie.setId(id);
//        movies.set(indexOfMovie,movie);
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping(value = "movie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
    }
}
