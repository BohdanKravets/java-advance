package com.javaadvance.validator;

import com.javaadvance.dto.MovieDto;
import com.javaadvance.entity.Movie;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class MovieValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return MovieDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MovieDto movie = (MovieDto) o;
        if(StringUtils.isNotBlank(movie.getTitle()) && !CharUtils.isAsciiAlphaUpper(movie.getTitle().charAt(0))){
            errors.rejectValue("movieTitle","movie.title.capital.letter",
                    "Movie title should start with capital letter");
        }
    }
}
