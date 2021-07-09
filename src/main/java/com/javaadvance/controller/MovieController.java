package com.javaadvance.controller;

import com.javaadvance.entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    private final List<Movie> movies = new ArrayList<>();

    {
        movies.add(new Movie(1, "Star Wars: New hope", 190));
        movies.add(new Movie(2, "LoTR: RoTK", 235));
    }

    //    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    @GetMapping(value = "/movie")
    public List<Movie> getMovies() {
        return movies;
    }

    @PostMapping(value = "/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie insertMovie(@RequestBody Movie movie) {
        movies.add(movie);
        return movie;
    }

    @PutMapping(value = "/movie/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie ) {
        final Optional<Movie> first = movies
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        final Movie movieInList = first
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No movie found"));
        final int indexOfMovie = movies.indexOf(movieInList);
        movie.setId(id);
        movies.set(indexOfMovie,movie);
        return  movie;
    }

    @DeleteMapping(value = "movie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id) {
        final boolean isRemoved = movies.removeIf(movie -> movie.getId() == id);
        if(isRemoved) {
            System.out.println("Movie is removed");
        } else {
            System.out.println("No movie with such id");
        }
    }
}
