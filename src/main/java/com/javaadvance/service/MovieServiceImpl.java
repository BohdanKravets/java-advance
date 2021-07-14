package com.javaadvance.service;

import com.javaadvance.dao.MovieDao;
import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public MoviePage getAllMovies(int page, int size) {
        final Page<Movie> movies = movieDao.findAll(PageRequest.of(page, size));
        final MoviePage moviePage = new MoviePage();
        moviePage.setMovies(movies.getContent());
        moviePage.setCurrentPage(movies.getNumber());
        moviePage.setLast(movies.isLast());
        moviePage.setTotalElements(movies.getTotalElements());
        return moviePage;
    }

    @Override
    public Movie getMovieById(int id) {
        return movieDao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Movie getMovieByTitle(String title) {
        final Optional<Movie> byTitle = movieDao.findByTitle(title);
        return byTitle.orElseThrow(() -> new ItemNotFoundException("Movie", "title", title));
    }


    @Override
    public Movie createMovie(Movie movie) {
        return movieDao.saveAndFlush(movie);
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        movie.setId(id);
        if (!movieDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return movieDao.saveAndFlush(movie);
    }

    @Override
    public void deleteMovie(int id) {
        if (!movieDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        movieDao.deleteById(id);
    }


}
