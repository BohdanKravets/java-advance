package com.javaadvance.service;

import com.javaadvance.dao.MovieDao;
import com.javaadvance.dto.MovieDto;
import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Movie;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private DirectorService directorService;

    @Override
    public MoviePage getAllMovies(int page, int size) {
        final Page<Movie> movies = movieDao.findAll(PageRequest.of(page, size));
        final MoviePage moviePage = new MoviePage();
        moviePage.setMovies(movies.getContent().stream().map(movie -> {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDto.setDuration(movie.getDuration());
            movieDto.setTitle(movie.getTitle());
            movieDto.setDirectorId(movie.getDirector().getId());
            return movieDto;
        }).collect(Collectors.toList()));
        moviePage.setCurrentPage(movies.getNumber());
        moviePage.setLast(movies.isLast());
        moviePage.setTotalElements(movies.getTotalElements());
        return moviePage;
    }

    @Override
    public MovieDto getMovieById(int id) {
        final Optional<Movie> byId = movieDao.findById(id);
        Optional<MovieDto> movieDto = Optional.of(new MovieDto(
                byId.get().getId(),
                byId.get().getTitle(),
                byId.get().getDuration(),
                byId.get().getDirector().getId()
        ));

        return movieDto.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public MovieDto getMovieByTitle(String title) {
        final Optional<Movie> byTitle = movieDao.findByTitle(title);
        Optional<MovieDto> movieDto = Optional.of(new MovieDto(
                byTitle.get().getId(),
                byTitle.get().getTitle(),
                byTitle.get().getDuration(),
                byTitle.get().getDirector().getId()
        ));
        return movieDto.orElseThrow(() -> new ItemNotFoundException("Movie", "title", title));
    }


    @Override
    public MovieDto createMovie(MovieDto movie) {
        Movie movieDB = new Movie();
        movieDB.setId(movie.getId());
        movieDB.setTitle(movie.getTitle());
        movieDB.setId(movie.getId());
        movieDB.setDuration(movie.getDuration());
        movieDB.setDirector(directorService.getDirectorById(movie.getDirectorId()));
        final Movie savedMovie = movieDao.saveAndFlush(movieDB);
        movie.setId(savedMovie.getId());
        return movie;
    }

    @Override
    public MovieDto updateMovie(int id, MovieDto movie) {
        movie.setId(id);
        if (!movieDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Movie movieDB = new Movie();
        movieDB.setId(movie.getId());
        movieDB.setTitle(movie.getTitle());
        movieDB.setId(movie.getId());
        movieDB.setDuration(movie.getDuration());
        movieDB.setDirector(directorService.getDirectorById(movie.getDirectorId()));
        movieDao.saveAndFlush(movieDB);
        return movie;
    }

    @Override
    public void deleteMovie(int id) {
        if (!movieDao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        movieDao.deleteById(id);
    }


}
