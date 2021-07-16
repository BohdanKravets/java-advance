package com.javaadvance.service;

import com.javaadvance.dao.DirectorDao;
import com.javaadvance.dto.DirectorDto;
import com.javaadvance.entity.Director;
import com.javaadvance.entity.Movie;
import com.javaadvance.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorDao directorDao;

    @Override
    public List<DirectorDto> getAllDirectors() {
        final List<Director> all = directorDao.findAll();
        final List<DirectorDto> directorDtos = all.stream().map(director -> {
                    DirectorDto directorDto = new DirectorDto();
                    directorDto.setId(director.getId());
                    directorDto.setName(director.getName());
                    final List<Integer> ids = director.getMovies().stream()
                            .map(Movie::getId)
                            .collect(Collectors.toList());
                    directorDto.setMovies(ids);
                    return directorDto;
                }
        ).collect(Collectors.toList());
        return directorDtos;
    }

    @Override
    public Director createDirector(Director director) {
        return directorDao.saveAndFlush(director);
    }

    @Override
    public Director updateDirector(int id, Director director) {
        director.setId(id);
        if (!directorDao.existsById(id)) {
            throw new ItemNotFoundException("Director", "id", String.valueOf(id));
        }
        return directorDao.saveAndFlush(director);
    }

    @Override
    public void deleteDirector(int id) {
        if (!directorDao.existsById(id)) {
            throw new ItemNotFoundException("Director", "id", String.valueOf(id));
        }
        directorDao.deleteById(id);
    }

    @Override
    public Director getDirectorById(int id) {
        return directorDao.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Director", "id", String.valueOf(id)));
    }
}
