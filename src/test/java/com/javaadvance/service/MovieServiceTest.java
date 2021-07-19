package com.javaadvance.service;

import com.javaadvance.dao.MovieDao;
import com.javaadvance.dto.MovieDto;
import com.javaadvance.dto.MoviePage;
import com.javaadvance.entity.Director;
import com.javaadvance.entity.Movie;
import com.javaadvance.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieDao movieDao;
    @Mock
    private DirectorService directorService;
    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    private void givePageAndSizeWhenGettingMoviesThenReturnMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Casino", 140, generateDirector(1)));
        movies.add(new Movie(2, "Kill Bill", 145, generateDirector(1)));
        final PageImpl<Movie> moviePage = new PageImpl<>(movies);

        Mockito.when(movieDao.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(moviePage);

        List<MovieDto> movieDtos = new ArrayList<>();
        final MovieDto movieDto1 = new MovieDto();
        movieDto1.setId(1);
        movieDto1.setDuration(140);
        movieDto1.setTitle("Casino");
        movieDto1.setDirectorId(1);
        final MovieDto movieDto2 = new MovieDto();
        movieDto2.setId(2);
        movieDto2.setDuration(145);
        movieDto2.setTitle("Kill Bill");
        movieDto2.setDirectorId(1);

        movieDtos.add(movieDto1);
        movieDtos.add(movieDto2);

        final MoviePage expectedResult = new MoviePage();
        expectedResult.setMovies(movieDtos);
        expectedResult.setTotalElements(2);

        final MoviePage actualResult = movieService.getAllMovies(0, 2);

        Assertions.assertEquals(expectedResult.getTotalElements(), actualResult.getTotalElements());
        Assertions.assertEquals(expectedResult.getMovies().get(0).getId(), actualResult.getMovies().get(0).getId());
        Assertions.assertEquals(expectedResult.getMovies().get(0).getTitle(), actualResult.getMovies().get(0).getTitle());


    }



    private Director generateDirector(int id) {
        return new Director(id, "tarantino", null, null);
    }
}