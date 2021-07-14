package com.javaadvance.service;

import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    MoviePage getAllMovies(int page, int size);

    Movie createMovie(Movie movie);

    Movie updateMovie(int id, Movie movie);

    void deleteMovie(int id);

    Movie getMovieById(int id);

    Movie getMovieByTitle(String title);
}
