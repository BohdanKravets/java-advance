package com.javaadvance.controller;


import com.javaadvance.dto.DirectorDto;
import com.javaadvance.entity.Director;
import com.javaadvance.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/director")
@RequiredArgsConstructor
@Slf4j
public class DirectorController {
    private final DirectorService directorService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DirectorDto> getDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Director getDirectorById(@PathVariable int id ) {
        return directorService.getDirectorById(id);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Director insertDirector(@RequestBody @Valid Director Director) {
        log.info("Handling Post request for object {}",Director);
        return directorService.createDirector(Director);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Director updateDirector(@PathVariable int id, @RequestBody @Valid Director Director) {
        return directorService.updateDirector(id, Director);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable int id) {
        directorService.deleteDirector(id);
    }
}
