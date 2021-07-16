package com.javaadvance.service;

import com.javaadvance.dto.DirectorDto;
import com.javaadvance.entity.Director;

import java.util.List;


public interface DirectorService {
    List<DirectorDto> getAllDirectors();

    Director createDirector(Director director);

    Director updateDirector(int id, Director director);

    void deleteDirector(int id);

    Director getDirectorById(int id);
}
