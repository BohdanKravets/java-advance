package com.javaadvance.service;

import com.javaadvance.dto.MovieDto;
import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;

public interface MovieService {

    MoviePage getAllMovies(int page, int size);

    MovieDto createMovie(MovieDto movie);

    Movie updateMovie(int id, Movie movie);

    void deleteMovie(int id);

    Movie getMovieById(int id);

    Movie getMovieByTitle(String title);
}
