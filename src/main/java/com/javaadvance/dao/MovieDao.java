package com.javaadvance.dao;

import com.javaadvance.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieDao extends JpaRepository<Movie,Integer> {

@Query("select m from Movie m where m.title like :title")
Optional<Movie> findByTitle(String title);
}
