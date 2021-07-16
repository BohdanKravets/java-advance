package com.javaadvance.service;

import com.javaadvance.dto.MovieDto;
import com.javaadvance.dto.MoviePage;

public interface MovieService {
    MoviePage getAllMovies(int page, int size);

    MovieDto createMovie( MovieDto movie);

    MovieDto updateMovie(int id, MovieDto movie);

    void deleteMovie(int id);

    MovieDto getMovieById(int id);

    MovieDto getMovieByTitle(String title);
}
